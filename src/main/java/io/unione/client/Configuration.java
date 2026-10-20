package io.unione.client;

import java.time.Duration;

/** API connection settings. */
public class Configuration {
    /** Base URL of the API. */
    public static final String DEFAULT_BASE_URL = "https://api.unione.io/en/transactional/api/v1";

    /** API host api.unione.io. */
    public static final String HOST_API = "api.unione.io";

    /** API host eu1.unione.io. */
    public static final String HOST_EU1 = "eu1.unione.io";

    /** API host us1.unione.io. */
    public static final String HOST_US1 = "us1.unione.io";

    /** Path of the API on every host. */
    public static final String BASE_PATH = "/en/transactional/api/v1";

    private final String apiKey;
    private String baseUrl = DEFAULT_BASE_URL;
    private Duration timeout = Duration.ofSeconds(30);
    private String userAgent = "unione-java-sdk/1.1.3";
    private String platform;

    /** @param apiKey user or project API key, sent in the X-API-KEY header */
    public Configuration(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Base URL for an API host: "eu1.unione.io" -&gt; "https://eu1.unione.io" + BASE_PATH; a full URL is kept as is.
     *
     * @param host API host (HOST_* constant) or a full base URL
     * @return base URL of the API
     */
    public static String baseUrlFor(String host) {
        if (host.contains("://")) {
            return host.replaceAll("/+$", "");
        }
        return "https://" + host.replaceAll("^/+|/+$", "") + BASE_PATH;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public Configuration setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl.replaceAll("/+$", "");
        return this;
    }

    public Duration getTimeout() {
        return timeout;
    }

    public Configuration setTimeout(Duration timeout) {
        this.timeout = timeout;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Configuration setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    /** The "platform" query parameter: "java-sdk.1.0.0" or, with an integration, "spring.1.0.0,java-sdk.1.0.0". */
    public String getPlatform() {
        return platform == null || platform.isEmpty() ? ApiClient.PLATFORM : platform + "," + ApiClient.PLATFORM;
    }

    /** Package that uses the SDK and its version, e.g. "spring.1.0.0": goes to the "platform" before the SDK one. */
    public Configuration setPlatform(String platform) {
        this.platform = platform;
        return this;
    }
}
