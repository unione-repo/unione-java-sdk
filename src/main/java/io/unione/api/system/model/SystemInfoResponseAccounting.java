package io.unione.api.system.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Object with accounting period properties. Present only if the API key used for request is the user
 * API key.
 */
public class SystemInfoResponseAccounting {
    /** Date and time of accounting period start in UTC in "YYYY-MM-DD hh:mm:ss" format. */
    private String periodStart;

    /** Date and time of accounting period end in UTC in "YYYY-MM-DD hh:mm:ss" format. */
    private String periodEnd;

    /** Number of emails included into accounting period. */
    private Long emailsIncluded;

    /**
     * Number of emails sent during accounting period. Can be bigger than emails_included in case of
     * overage.
     */
    private Long emailsSent;

    public String getPeriodStart() {
        return periodStart;
    }

    public SystemInfoResponseAccounting setPeriodStart(String periodStart) {
        this.periodStart = periodStart;
        return this;
    }

    public String getPeriodEnd() {
        return periodEnd;
    }

    public SystemInfoResponseAccounting setPeriodEnd(String periodEnd) {
        this.periodEnd = periodEnd;
        return this;
    }

    public Long getEmailsIncluded() {
        return emailsIncluded;
    }

    public SystemInfoResponseAccounting setEmailsIncluded(Long emailsIncluded) {
        this.emailsIncluded = emailsIncluded;
        return this;
    }

    public Long getEmailsSent() {
        return emailsSent;
    }

    public SystemInfoResponseAccounting setEmailsSent(Long emailsSent) {
        this.emailsSent = emailsSent;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static SystemInfoResponseAccounting fromMap(Map<String, Object> data) {
        SystemInfoResponseAccounting result = new SystemInfoResponseAccounting();
        result.periodStart = Json.asString(data.get("period_start"));
        result.periodEnd = Json.asString(data.get("period_end"));
        result.emailsIncluded = Json.asLong(data.get("emails_included"));
        result.emailsSent = Json.asLong(data.get("emails_sent"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "period_start", periodStart);
        Json.put(result, "period_end", periodEnd);
        Json.put(result, "emails_included", emailsIncluded);
        Json.put(result, "emails_sent", emailsSent);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
