package io.unione.api.suppression.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** SuppressionListResponse */
public class SuppressionListResponse {
    /**
     * "success" string
     * Allowed values: `success`.
     */
    private String status;

    /** Array of suppression objects. */
    private List<SuppressionListObject> suppressions;

    /**
     * The parameter indicates from which position the selection is to be started. Must be empty or omitted
     * for the first data chunk. In order to get subsequent chunks, you must set the "cursor" parameter in
     * your request, using the value received in response to the previous request.
     */
    private String cursor;

    public String getStatus() {
        return status;
    }

    public SuppressionListResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public List<SuppressionListObject> getSuppressions() {
        return suppressions;
    }

    public SuppressionListResponse setSuppressions(List<SuppressionListObject> suppressions) {
        this.suppressions = suppressions;
        return this;
    }

    public String getCursor() {
        return cursor;
    }

    public SuppressionListResponse setCursor(String cursor) {
        this.cursor = cursor;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static SuppressionListResponse fromMap(Map<String, Object> data) {
        SuppressionListResponse result = new SuppressionListResponse();
        result.status = Json.asString(data.get("status"));
        result.suppressions = Json.asList(data.get("suppressions"), v0 -> Json.asObject(v0, SuppressionListObject::fromMap));
        result.cursor = Json.asString(data.get("cursor"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "status", status);
        Json.put(result, "suppressions", Json.mapList(suppressions, v0 -> v0.toMap()));
        Json.put(result, "cursor", cursor);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
