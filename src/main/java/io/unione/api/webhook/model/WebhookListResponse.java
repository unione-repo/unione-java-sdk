package io.unione.api.webhook.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** WebhookListResponse */
public class WebhookListResponse {
    /**
     * "success" string
     * Allowed values: `success`.
     */
    private String status;

    /** Array of webhooks objects. */
    private List<WebhookObject> objects;

    public String getStatus() {
        return status;
    }

    public WebhookListResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public List<WebhookObject> getObjects() {
        return objects;
    }

    public WebhookListResponse setObjects(List<WebhookObject> objects) {
        this.objects = objects;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static WebhookListResponse fromMap(Map<String, Object> data) {
        WebhookListResponse result = new WebhookListResponse();
        result.status = Json.asString(data.get("status"));
        result.objects = Json.asList(data.get("objects"), v0 -> Json.asObject(v0, WebhookObject::fromMap));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "status", status);
        Json.put(result, "objects", Json.mapList(objects, v0 -> v0.toMap()));
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
