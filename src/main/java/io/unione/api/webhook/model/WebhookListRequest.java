package io.unione.api.webhook.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** WebhookListRequest */
public class WebhookListRequest {
    /** Limits the number of webhooks returned. 50 is recommended. */
    private Integer limit;

    /**
     * Index of the first webhook in the list, starting with 0.
     * Default: `0`.
     */
    private Integer offset;

    public Integer getLimit() {
        return limit;
    }

    public WebhookListRequest setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public WebhookListRequest setOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static WebhookListRequest fromMap(Map<String, Object> data) {
        WebhookListRequest result = new WebhookListRequest();
        result.limit = Json.asInteger(data.get("limit"));
        result.offset = Json.asInteger(data.get("offset"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "limit", limit);
        Json.put(result, "offset", offset);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
