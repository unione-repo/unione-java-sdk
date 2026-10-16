package io.unione.api.webhook.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** WebhookDeleteRequest */
public class WebhookDeleteRequest {
    /** Webhook URL. */
    private String url;

    public String getUrl() {
        return url;
    }

    public WebhookDeleteRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static WebhookDeleteRequest fromMap(Map<String, Object> data) {
        WebhookDeleteRequest result = new WebhookDeleteRequest();
        result.url = Json.asString(data.get("url"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "url", url);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
