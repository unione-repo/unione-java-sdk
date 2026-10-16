package io.unione.api.eventdump.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** EventDumpGetResponse */
public class EventDumpGetResponse {
    /**
     * "success" string
     * Allowed values: `success`.
     */
    private String status;

    /** An object with the properties of the event dump. */
    private EventDumpObject eventDump;

    public String getStatus() {
        return status;
    }

    public EventDumpGetResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public EventDumpObject getEventDump() {
        return eventDump;
    }

    public EventDumpGetResponse setEventDump(EventDumpObject eventDump) {
        this.eventDump = eventDump;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static EventDumpGetResponse fromMap(Map<String, Object> data) {
        EventDumpGetResponse result = new EventDumpGetResponse();
        result.status = Json.asString(data.get("status"));
        result.eventDump = Json.asObject(data.get("event_dump"), EventDumpObject::fromMap);
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "status", status);
        Json.put(result, "event_dump", eventDump == null ? null : eventDump.toMap());
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
