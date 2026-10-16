package io.unione.api.obsolete.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** ObsoleteUnsubscribedListResponse */
public class ObsoleteUnsubscribedListResponse {
    /**
     * "success" string
     * Allowed values: `success`.
     */
    private String status;

    /** Array of object describing unsubscribed emails. */
    private List<ObsoleteUnsubscribedListResponseUnsubscribedItem> unsubscribed;

    public String getStatus() {
        return status;
    }

    public ObsoleteUnsubscribedListResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public List<ObsoleteUnsubscribedListResponseUnsubscribedItem> getUnsubscribed() {
        return unsubscribed;
    }

    public ObsoleteUnsubscribedListResponse setUnsubscribed(List<ObsoleteUnsubscribedListResponseUnsubscribedItem> unsubscribed) {
        this.unsubscribed = unsubscribed;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static ObsoleteUnsubscribedListResponse fromMap(Map<String, Object> data) {
        ObsoleteUnsubscribedListResponse result = new ObsoleteUnsubscribedListResponse();
        result.status = Json.asString(data.get("status"));
        result.unsubscribed = Json.asList(data.get("unsubscribed"), v0 -> Json.asObject(v0, ObsoleteUnsubscribedListResponseUnsubscribedItem::fromMap));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "status", status);
        Json.put(result, "unsubscribed", Json.mapList(unsubscribed, v0 -> v0.toMap()));
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
