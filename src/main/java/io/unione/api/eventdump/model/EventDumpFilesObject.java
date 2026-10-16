package io.unione.api.eventdump.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** An object with the properties of the event dump file. */
public class EventDumpFilesObject {
    /**
     * Use this unique URL to download the requested dump file (either .csv or .csv.gz, depending on your
     * request).
     */
    private String url;

    /** File size. */
    private Long size;

    public String getUrl() {
        return url;
    }

    public EventDumpFilesObject setUrl(String url) {
        this.url = url;
        return this;
    }

    public Long getSize() {
        return size;
    }

    public EventDumpFilesObject setSize(Long size) {
        this.size = size;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static EventDumpFilesObject fromMap(Map<String, Object> data) {
        EventDumpFilesObject result = new EventDumpFilesObject();
        result.url = Json.asString(data.get("url"));
        result.size = Json.asLong(data.get("size"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "url", url);
        Json.put(result, "size", size);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
