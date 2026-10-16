package io.unione;

import io.unione.client.Configuration;

/**
 * Shortcut to create the UniOne API client.
 * <p>
 * UniOneClient client = UniOne.client("YOUR_API_KEY");
 */
public final class UniOne {
    private UniOne() {
    }

    /** &#64;param apiKey user or project API key */
    public static UniOneClient client(String apiKey) {
        return new UniOneClient(apiKey);
    }

    /**
     * &#64;param apiKey user or project API key
     * &#64;param host API host (Configuration.HOST_US1, "us1.unione.io") or a full base URL
     */
    public static UniOneClient client(String apiKey, String host) {
        return new UniOneClient(new Configuration(apiKey).setBaseUrl(Configuration.baseUrlFor(host)));
    }

    /** &#64;param config API connection settings (base URL, timeout, ...) */
    public static UniOneClient client(Configuration config) {
        return new UniOneClient(config);
    }
}
