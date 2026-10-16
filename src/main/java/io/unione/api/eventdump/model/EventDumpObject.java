package io.unione.api.eventdump.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** An object with the properties of the event dump. */
public class EventDumpObject {
    /**
     * Dump ID received by the method
     * [event-dump/create](https://docs.unione.io/en/web-api-ref#event-dump-create).
     */
    private String dumpId;

    /**
     * Task status, possible values are:
     * <p>
     * - __queued__ - the task is in the queue, processing is not started yet;
     * - __in_process__ - the request is being processed;
     * - __ready__ - the task is finished, dump file is ready for download;
     * - __failed__ -  processing failed due to an internal error.
     */
    private String dumpStatus;

    /**
     * An array of objects, each representing a file, ready for download. You may start downloading
     * everything listed in “files” even if "dump_status" is still "in_process". If there are no events,
     * according to the specified parameters, an empty array will be returned.
     */
    private List<EventDumpFilesObject> files;

    public String getDumpId() {
        return dumpId;
    }

    public EventDumpObject setDumpId(String dumpId) {
        this.dumpId = dumpId;
        return this;
    }

    public String getDumpStatus() {
        return dumpStatus;
    }

    public EventDumpObject setDumpStatus(String dumpStatus) {
        this.dumpStatus = dumpStatus;
        return this;
    }

    public List<EventDumpFilesObject> getFiles() {
        return files;
    }

    public EventDumpObject setFiles(List<EventDumpFilesObject> files) {
        this.files = files;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static EventDumpObject fromMap(Map<String, Object> data) {
        EventDumpObject result = new EventDumpObject();
        result.dumpId = Json.asString(data.get("dump_id"));
        result.dumpStatus = Json.asString(data.get("dump_status"));
        result.files = Json.asList(data.get("files"), v0 -> Json.asObject(v0, EventDumpFilesObject::fromMap));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "dump_id", dumpId);
        Json.put(result, "dump_status", dumpStatus);
        Json.put(result, "files", Json.mapList(files, v0 -> v0.toMap()));
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
