package io.unione.api.eventdump.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** EventDumpCreateResponse */
public class EventDumpCreateResponse {
    /**
     * "success" string
     * Allowed values: `success`.
     */
    private String status;

    /**
     * Dump identifier, used to call the
     * [event-dump/get](https://docs.unione.io/en/web-api-ref#event-dump-get) method.
     */
    private String dumpId;

    public String getStatus() {
        return status;
    }

    public EventDumpCreateResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getDumpId() {
        return dumpId;
    }

    public EventDumpCreateResponse setDumpId(String dumpId) {
        this.dumpId = dumpId;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static EventDumpCreateResponse fromMap(Map<String, Object> data) {
        EventDumpCreateResponse result = new EventDumpCreateResponse();
        result.status = Json.asString(data.get("status"));
        result.dumpId = Json.asString(data.get("dump_id"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "status", status);
        Json.put(result, "dump_id", dumpId);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
