package io.unione.client;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/** Low level transport: every API method is a POST request with a JSON body that returns JSON. */
public class ApiClient {
    /** The SDK and its version in the "platform" query parameter of every request. */
    public static final String PLATFORM = "java-sdk.2.2.3";

    private final Configuration config;
    private final HttpClient httpClient;

    public ApiClient(Configuration config) {
        this(config, HttpClient.newBuilder().connectTimeout(config.getTimeout()).build());
    }

    public ApiClient(Configuration config, HttpClient httpClient) {
        this.config = config;
        this.httpClient = httpClient;
    }

    /**
     * Calls an API method and returns the decoded response.
     *
     * @param path method path, e.g. "/email/send.json"
     * @param body request body, null for an empty one
     * @throws ApiException when the API returns an error
     */
    public Map<String, Object> call(String path, Map<String, Object> body) {
        String json = Json.stringify(body == null ? new LinkedHashMap<String, Object>() : body);
        String url = config.getBaseUrl() + "/" + path.replaceFirst("^/+", "")
            + "?platform=" + URLEncoder.encode(config.getPlatform(), StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
            .timeout(config.getTimeout())
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .header("User-Agent", config.getUserAgent())
            .header("X-API-KEY", config.getApiKey())
            .POST(HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8))
            .build();

        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new ApiException("HTTP request failed: " + e.getMessage(), 0, null, null, e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException("HTTP request interrupted", 0, null, null, e);
        }

        int status = response.statusCode();
        Map<String, Object> data;
        try {
            data = Json.asMap(Json.parse(response.body()));
        } catch (IllegalArgumentException e) {
            throw new ApiException("Invalid JSON in response (HTTP " + status + "): " + ApiException.quote(response.body()),
                status, null, null, response.body(), e);
        }
        if (data == null) {
            throw new ApiException("Unexpected response (HTTP " + status + "): " + ApiException.quote(response.body()),
                status, null, null, response.body(), null);
        }
        if (status >= 400 || "error".equals(data.get("status"))) {
            Object message = data.get("message");
            throw new ApiException(message == null ? "API error" : ApiException.quote(message.toString()), status,
                Json.asInteger(data.get("code")), data, response.body(), null);
        }
        return data;
    }
}
