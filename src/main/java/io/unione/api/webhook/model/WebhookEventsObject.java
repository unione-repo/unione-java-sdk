package io.unione.api.webhook.model;

import io.unione.client.Json;
import io.unione.common.model.EmailStatus;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Object containing events to notify of. */
public class WebhookEventsObject {
    /**
     * If present then spam block events will be reported. Should contain single array element with "*"
     * string.
     */
    private List<String> spamBlock;

    /**
     * If present then email status change events will be reported. Contains names of statuses to notify
     * of.
     * <p>
     * Raw value, kept also when the SDK does not know it.
     */
    private List<String> emailStatus;

    public List<String> getSpamBlock() {
        return spamBlock;
    }

    public WebhookEventsObject setSpamBlock(List<String> spamBlock) {
        this.spamBlock = spamBlock;
        return this;
    }

    /**
     * Enum value of the field, UNKNOWN_VALUE for a value unknown to this SDK version (the raw one:
     * getEmailStatusValue()), null when absent.
     */
    public List<EmailStatus> getEmailStatus() {
        return Json.asList(emailStatus, EmailStatus::fromValue);
    }

    /**
     * Sets the field; IllegalArgumentException for UNKNOWN_VALUE (an unknown value goes to
     * setEmailStatusValue()).
     */
    public WebhookEventsObject setEmailStatus(List<EmailStatus> emailStatus) {
        this.emailStatus = Json.convertList(emailStatus, EmailStatus::toValue);
        return this;
    }

    /** Raw value of the field as sent by the API, also when the SDK does not know it. */
    public List<String> getEmailStatusValue() {
        return emailStatus;
    }

    /** Sets the raw value, e.g. one added to the API after this SDK version. */
    public WebhookEventsObject setEmailStatusValue(List<String> emailStatus) {
        this.emailStatus = emailStatus;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static WebhookEventsObject fromMap(Map<String, Object> data) {
        WebhookEventsObject result = new WebhookEventsObject();
        result.spamBlock = Json.asList(data.get("spam_block"), Json::asString);
        result.emailStatus = Json.asList(data.get("email_status"), Json::asString);
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "spam_block", spamBlock);
        Json.put(result, "email_status", emailStatus);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
