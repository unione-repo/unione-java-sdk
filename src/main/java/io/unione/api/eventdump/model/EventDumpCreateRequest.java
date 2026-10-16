package io.unione.api.eventdump.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** EventDumpCreateRequest */
public class EventDumpCreateRequest {
    /**
     * Date and time in YYYY-MM-DD hh:mm:ss format, specifying period start time. Data is stored for up to
     * 32 days, depending on your tariff.
     */
    private String startTime;

    /** Date and time in YYYY-MM-DD hh:mm:ss format, specifying period end time (non-inclusive). */
    private String endTime;

    /**
     * Maximum number of events returned (default is 50). If this value is over 100 000, several files will
     * be created, each having 100 000 events maximum.
     */
    private Integer limit;

    /**
     * For accounts with projects enabled, the value all_projects=true allows to fetch data for all
     * existing projects.
     * Default: `False`.
     */
    private Boolean allProjects;

    /** An object with the properties of the event dump filter. */
    private FilterObject filter;

    /**
     * An array of strings containing the names of columns you need in your report, in your preferred
     * order. If the 'dump_fields' array is missing or empty, a default field list and order will be
     * applied. Allowed values, in their default order, are - 'project_id', 'event_time', 'job_id',
     * 'from_email', 'email', 'status', 'delivery_status', 'metadata', 'destination_response',
     * 'user_agent', 'url', 'ip', 'tags'. For maximum security, the 'subject' field is not included by
     * default, but you can add it by explicitly including in the 'dump_fields' array.
     */
    private List<String> dumpFields;

    /**
     * Allows you to obtain aggregated statistics for a given time period. The two allowed values are:
     * <p>
     * - __day_status__ - fetch aggregated statistics (the filter value must be missing or empty, as filters are not allowed with this option);
     * - __an empty string__ - used by default, event_dump/create works as if the aggregate parameter is omitted.
     */
    private String aggregate;

    /** Field delimiter, can be set to either  ',' or ';', defaults to  ','. */
    private String delimiter;

    /**
     * File format, either “csv” (default) or “csv_gzip”.
     * Default: `csv`.
     */
    private String format;

    public String getStartTime() {
        return startTime;
    }

    public EventDumpCreateRequest setStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public EventDumpCreateRequest setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public EventDumpCreateRequest setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Boolean getAllProjects() {
        return allProjects;
    }

    public EventDumpCreateRequest setAllProjects(Boolean allProjects) {
        this.allProjects = allProjects;
        return this;
    }

    public FilterObject getFilter() {
        return filter;
    }

    public EventDumpCreateRequest setFilter(FilterObject filter) {
        this.filter = filter;
        return this;
    }

    public List<String> getDumpFields() {
        return dumpFields;
    }

    public EventDumpCreateRequest setDumpFields(List<String> dumpFields) {
        this.dumpFields = dumpFields;
        return this;
    }

    public String getAggregate() {
        return aggregate;
    }

    public EventDumpCreateRequest setAggregate(String aggregate) {
        this.aggregate = aggregate;
        return this;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public EventDumpCreateRequest setDelimiter(String delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    public String getFormat() {
        return format;
    }

    public EventDumpCreateRequest setFormat(String format) {
        this.format = format;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static EventDumpCreateRequest fromMap(Map<String, Object> data) {
        EventDumpCreateRequest result = new EventDumpCreateRequest();
        result.startTime = Json.asString(data.get("start_time"));
        result.endTime = Json.asString(data.get("end_time"));
        result.limit = Json.asInteger(data.get("limit"));
        result.allProjects = Json.asBoolean(data.get("all_projects"));
        result.filter = Json.asObject(data.get("filter"), FilterObject::fromMap);
        result.dumpFields = Json.asList(data.get("dump_fields"), Json::asString);
        result.aggregate = Json.asString(data.get("aggregate"));
        result.delimiter = Json.asString(data.get("delimiter"));
        result.format = Json.asString(data.get("format"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "start_time", startTime);
        Json.put(result, "end_time", endTime);
        Json.put(result, "limit", limit);
        Json.put(result, "all_projects", allProjects);
        Json.put(result, "filter", filter == null ? null : filter.toMap());
        Json.put(result, "dump_fields", dumpFields);
        Json.put(result, "aggregate", aggregate);
        Json.put(result, "delimiter", delimiter);
        Json.put(result, "format", format);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
