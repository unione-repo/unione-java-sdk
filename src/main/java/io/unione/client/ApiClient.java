package io.unione.client;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpTimeoutException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/** Low level transport: every API method is a POST request with a JSON body that returns JSON. */
public class ApiClient {
    /** The SDK and its version in the "platform" query parameter of every request. */
    public static final String PLATFORM = "java-sdk.2.3.0";

    private final Configuration config;
    private final HttpClient httpClient;

    /**
     * A client with its own HttpClient.
     *
     * @param config connection settings
     */
    public ApiClient(Configuration config) {
        this(config, HttpClient.newBuilder().connectTimeout(config.getTimeout()).build());
    }

    /**
     * A client that sends the requests with the given HttpClient.
     *
     * @param config connection settings
     * @param httpClient HTTP client
     */
    public ApiClient(Configuration config, HttpClient httpClient) {
        this.config = config;
        this.httpClient = httpClient;
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
        } catch (HttpTimeoutException e) {
            throw transportError(method, "HTTP request timed out after " + ErrorInfo.seconds(config.getTimeout()) + " s",
                e, ErrorKind.TIMEOUT);
        } catch (IOException e) {
            throw transportError(method, "HTTP request failed: " + e, e, ErrorKind.NETWORK);
        } catch (InterruptedException e) {
            // cancelled by the caller: keep the interrupt flag; kind NETWORK, isRetryable() is false
            Thread.currentThread().interrupt();
            throw transportError(method, "HTTP request interrupted", e, ErrorKind.NETWORK);
        }

        int status = response.statusCode();
        Map<String, Object> data;
        try {
            data = Json.asMap(Json.parse(response.body()));
        } catch (IllegalArgumentException e) {
            throw new ApiException(method + ": invalid JSON in response (HTTP " + status + "): " + ApiException.quote(response.body()),
                status, null, null, response.body(), e, method, null, ErrorKind.INVALID_RESPONSE);
        }
        if (data == null) {
            throw new ApiException(method + ": unexpected response (HTTP " + status + "): " + ApiException.quote(response.body()),
                status, null, null, response.body(), null, method, null, ErrorKind.INVALID_RESPONSE);
        }
        if (status >= 400 || "error".equals(data.get("status"))) {
            Object message = data.get("message");
            String apiMessage = message == null ? null : Json.asString(message);
            Integer code = ErrorInfo.code(data.get("code"));
            throw new ApiException(ErrorInfo.apiMessage(method, ApiException.quote(apiMessage), status, code),
                status, code, data, response.body(), null, method, ErrorInfo.hint(code), ErrorKind.API, apiMessage);
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
}
