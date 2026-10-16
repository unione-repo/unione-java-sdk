package io.unione.api.system.model;

import io.unione.client.Json;
import io.unione.common.model.EmailCounterMode;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Object with email counter properties. Present only if the API key used for request is the user API
 * key.
 */
public class ProjectAccountingObject {
    /** Project's email counter value. It increases on every email sent. */
    private Long emailCounter;

    /**
     * Allows you to limit the number of emails sent by the project. The sending stops when the limit is
     * reached. Any non-negative 64-bit integer is acceptable. 0 means no limit.
     */
    private Long emailCounterLimit;

    /**
     * 'default' - the counter resets to zero automatically at the beginning of each accounting period.
     * 'permanent' - the counter never resets automatically.
     * Default: `default`.
     * <p>
     * Raw value, kept also when the SDK does not know it.
     */
    private String emailCounterMode;

    public Long getEmailCounter() {
        return emailCounter;
    }

    public ProjectAccountingObject setEmailCounter(Long emailCounter) {
        this.emailCounter = emailCounter;
        return this;
    }

    public Long getEmailCounterLimit() {
        return emailCounterLimit;
    }

    public ProjectAccountingObject setEmailCounterLimit(Long emailCounterLimit) {
        this.emailCounterLimit = emailCounterLimit;
        return this;
    }

    /**
     * Enum value of the field, UNKNOWN_VALUE for a value unknown to this SDK version (the raw one:
     * getEmailCounterModeValue()), null when absent.
     */
    public EmailCounterMode getEmailCounterMode() {
        return EmailCounterMode.fromValue(emailCounterMode);
    }

    /**
     * Sets the field; IllegalArgumentException for UNKNOWN_VALUE (an unknown value goes to
     * setEmailCounterModeValue()).
     */
    public ProjectAccountingObject setEmailCounterMode(EmailCounterMode emailCounterMode) {
        this.emailCounterMode = EmailCounterMode.toValue(emailCounterMode);
        return this;
    }

    /** Raw value of the field as sent by the API, also when the SDK does not know it. */
    public String getEmailCounterModeValue() {
        return emailCounterMode;
    }

    /** Sets the raw value, e.g. one added to the API after this SDK version. */
    public ProjectAccountingObject setEmailCounterModeValue(String emailCounterMode) {
        this.emailCounterMode = emailCounterMode;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static ProjectAccountingObject fromMap(Map<String, Object> data) {
        ProjectAccountingObject result = new ProjectAccountingObject();
        result.emailCounter = Json.asLong(data.get("email_counter"));
        result.emailCounterLimit = Json.asLong(data.get("email_counter_limit"));
        result.emailCounterMode = Json.asString(data.get("email_counter_mode"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "email_counter", emailCounter);
        Json.put(result, "email_counter_limit", emailCounterLimit);
        Json.put(result, "email_counter_mode", emailCounterMode);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
