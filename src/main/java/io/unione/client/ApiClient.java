package io.unione.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/** Low level transport: every API method is a POST request with a JSON body that returns JSON. */
public class ApiClient {
    /** The SDK and its version in the "platform" query parameter of every request. */
    public static final String PLATFORM = "java-sdk.1.2.4";

    private final Configuration config;

    public ApiClient(Configuration config) {
        this.config = config;
    }

    /**
     * Opens the connection of a request: override to set a proxy, SSL settings and so on.
     *
     * @param url full URL of the request
     * @return connection to send the request with
     * @throws IOException when the connection can not be opened
     */
    protected HttpURLConnection openConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    /**
     * Calls an API method and returns the decoded response.
     *
     * @param path method path, e.g. "/email/send.json"
     * @param body request body, null for an empty one
     * @return decoded response
     * @throws ApiException when the API returns an error
     */
    public Map<String, Object> call(String path, Map<String, Object> body) {
        String json = Json.stringify(body == null ? new LinkedHashMap<String, Object>() : body);
        String url;
        try {
            url = config.getBaseUrl() + "/" + path.replaceFirst("^/+", "")
                + "?platform=" + URLEncoder.encode(config.getPlatform(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
        int timeout = (int) Math.min(Integer.MAX_VALUE, config.getTimeout().toMillis());

        int status;
        String text;
        HttpURLConnection connection = null;
        try {
            connection = openConnection(new URL(url));
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("User-Agent", config.getUserAgent());
            connection.setRequestProperty("X-API-KEY", config.getApiKey());
            byte[] payload = json.getBytes(StandardCharsets.UTF_8);
            connection.setFixedLengthStreamingMode(payload.length);
            try (OutputStream out = connection.getOutputStream()) {
                out.write(payload);
            }
            status = connection.getResponseCode();
            text = read(status >= 400 ? connection.getErrorStream() : connection.getInputStream());
        } catch (IOException e) {
            throw new ApiException("HTTP request failed: " + e.getMessage(), 0, null, null, e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        Map<String, Object> data;
        try {
            data = Json.asMap(Json.parse(text));
        } catch (IllegalArgumentException e) {
            throw new ApiException("Invalid JSON in response (HTTP " + status + "): " + ApiException.quote(text),
                status, null, null, text, e);
        }
        if (data == null) {
            throw new ApiException("Unexpected response (HTTP " + status + "): " + ApiException.quote(text),
                status, null, null, text, null);
        }
        if (status >= 400 || "error".equals(data.get("status"))) {
            Object message = data.get("message");
            throw new ApiException(message == null ? "API error" : ApiException.quote(message.toString()), status,
                Json.asInteger(data.get("code")), data, text, null);
        }
        return data;
    }

    private static String read(InputStream stream) throws IOException {
        if (stream == null) {
            return "";
        }
        try (InputStream in = stream) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];
            int n;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
            return new String(out.toByteArray(), StandardCharsets.UTF_8);
        }
    }
}
