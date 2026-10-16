package io.unione.api.suppression.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** SuppressionListRequest */
public class SuppressionListRequest {
    /**
     * Cause of email being suppressed. One of:
     * <p>
     * - __unsubscribed__ - email is unsubscribed;
     * - __temporary_unavailable__ - the email address is unavailable. This means that over the next three days sending to this address will return an error. Email may be temporarily unavailable due to several reasons, e.g.:
     * <p>
     * - a previous email has been rejected by the recipient's server for spam;
     * - the recipient's mailbox is full or is not used;
     * - recipient's domain does not accept mail;
     * - sending server was rejected due to blacklisting;
     * <p>
     * - __permanent_unavailable__ - the email address is permanently unavailable due to multiple hard bounces;
     * - __complained__ -  the recipient reported spam in the previous emails;
     * - __blocked__ -  sending to the email is prohibited by administration of UniOne.
     * We may add some new causes in the future.
     */
    private String cause;

    /**
     * Source of email being suppressed. One of:
     * <p>
     * - __user__ - suppressed by user with    [suppression/set](https://docs.unione.io/en/web-api-ref#suppression-set);
     * - __system__ - sending to the email is prohibited by system, for example due to multiple hard bounces;
     * - __subscriber__ - the recipient reported spam or unsubscribed in the previous emails.
     */
    private String source;

    /**
     * Date in the format YYYY-MM-DD hh:mm:ss to get suppression list from the "start_time" to the present
     * day. Ignored if "cursor" is not empty.
     */
    private String startTime;

    /**
     * The parameter indicates from which position the selection is to be started. Must be empty or omitted
     * for the first data chunk. In order to get subsequent chunks, you must set the "cursor" parameter in
     * your request, using the value received in response to the previous request.
     */
    private String cursor;

    /** Limits the number of records to be returned at one time, default is 50. */
    private Integer limit;

    public String getCause() {
        return cause;
    }

    public SuppressionListRequest setCause(String cause) {
        this.cause = cause;
        return this;
    }

    public String getSource() {
        return source;
    }

    public SuppressionListRequest setSource(String source) {
        this.source = source;
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public SuppressionListRequest setStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getCursor() {
        return cursor;
    }

    public SuppressionListRequest setCursor(String cursor) {
        this.cursor = cursor;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public SuppressionListRequest setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static SuppressionListRequest fromMap(Map<String, Object> data) {
        SuppressionListRequest result = new SuppressionListRequest();
        result.cause = Json.asString(data.get("cause"));
        result.source = Json.asString(data.get("source"));
        result.startTime = Json.asString(data.get("start_time"));
        result.cursor = Json.asString(data.get("cursor"));
        result.limit = Json.asInteger(data.get("limit"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "cause", cause);
        Json.put(result, "source", source);
        Json.put(result, "start_time", startTime);
        Json.put(result, "cursor", cursor);
        Json.put(result, "limit", limit);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
