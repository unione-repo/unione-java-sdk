package io.unione.api.eventdump.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** EventDumpDeleteRequest */
public class EventDumpDeleteRequest {
    /**
     * Dump identifier, obtained via the
     * [event-dump/create](https://docs.unione.io/en/web-api-ref#event-dump-create) method.
     */
    private String dumpId;

    public String getDumpId() {
        return dumpId;
    }

    public EventDumpDeleteRequest setDumpId(String dumpId) {
        this.dumpId = dumpId;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static EventDumpDeleteRequest fromMap(Map<String, Object> data) {
        EventDumpDeleteRequest result = new EventDumpDeleteRequest();
        result.dumpId = Json.asString(data.get("dump_id"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "dump_id", dumpId);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
