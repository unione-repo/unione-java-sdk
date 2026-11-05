package io.unione.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/** Low level transport: every API method is a POST request with a JSON body that returns JSON. */
public class ApiClient {
    /** The SDK and its version in the "platform" query parameter of every request. */
    public static final String PLATFORM = "java-sdk.1.3.0";

    private final Configuration config;

    /**
     * A client of the settings.
     *
     * @param config connection settings
     */
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
     * @throws ApiException when the API returns an error, the request fails or is invalid (an empty API key, a
     *     required field missing) or the response can not be processed
     */
    public Map<String, Object> call(String path, Map<String, Object> body) {
        String method = ErrorInfo.method(path);
        String apiKey = config.getApiKey();
        if (apiKey == null || apiKey.isEmpty()) {
            throw invalidRequest(method, "the API key is empty: pass the API key of the user or of the project to the client");
        }
        Map<String, Object> payload = body == null ? new LinkedHashMap<String, Object>() : body;
        // the required fields are checked on the map itself (only for the methods that have them), not on its JSON
        String missing = ErrorInfo.missing(method, payload);
        if (missing != null) {
            throw invalidRequest(method, missing + " is required");
        }
        String json = Json.stringify(payload);
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
            byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
            connection.setFixedLengthStreamingMode(bytes.length);
            try (OutputStream out = connection.getOutputStream()) {
                out.write(bytes);
            }
            status = connection.getResponseCode();
            text = read(status >= 400 ? connection.getErrorStream() : connection.getInputStream());
        } catch (SocketTimeoutException e) {
            throw transportError(method, "HTTP request timed out after " + ErrorInfo.seconds(config.getTimeout()) + " s",
                e, ErrorKind.TIMEOUT);
        } catch (IOException e) {
            throw transportError(method, "HTTP request failed: " + e, e, ErrorKind.NETWORK);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        Map<String, Object> data;
        try {
            data = Json.asMap(Json.parse(text));
        } catch (IllegalArgumentException e) {
            throw new ApiException(method + ": invalid JSON in response (HTTP " + status + "): " + ApiException.quote(text),
                status, null, null, text, e, method, null, ErrorKind.INVALID_RESPONSE);
        }
        if (data == null) {
            throw new ApiException(method + ": unexpected response (HTTP " + status + "): " + ApiException.quote(text),
                status, null, null, text, null, method, null, ErrorKind.INVALID_RESPONSE);
        }
        if (status >= 400 || "error".equals(data.get("status"))) {
            Object message = data.get("message");
            String apiMessage = message == null ? null : Json.asString(message);
            Integer code = ErrorInfo.code(data.get("code"));
            throw new ApiException(ErrorInfo.apiMessage(method, ApiException.quote(apiMessage), status, code),
                status, code, data, text, null, method, ErrorInfo.hint(code), ErrorKind.API, apiMessage);
        }
        return data;
    }

    private static ApiException invalidRequest(String method, String message) {
        return new ApiException(method + ": " + message, 0, null, null, null, null, method, null,
            ErrorKind.INVALID_REQUEST);
    }

    private static ApiException transportError(String method, String message, Throwable cause, ErrorKind kind) {
        return new ApiException(method + ": " + message, 0, null, null, null, cause, method, null, kind);
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
