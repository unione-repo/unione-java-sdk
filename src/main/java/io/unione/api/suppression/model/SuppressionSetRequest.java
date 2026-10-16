package io.unione.api.suppression.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** SuppressionSetRequest */
public class SuppressionSetRequest {
    /** Email to add in suppression list. */
    private String email;

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
     */
    private String cause;

    /** When suppression was created, in UTC timezone in "YYYY-MM-DD hh:mm:ss" format. */
    private String created;

    public String getEmail() {
        return email;
    }

    public SuppressionSetRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCause() {
        return cause;
    }

    public SuppressionSetRequest setCause(String cause) {
        this.cause = cause;
        return this;
    }

    public String getCreated() {
        return created;
    }

    public SuppressionSetRequest setCreated(String created) {
        this.created = created;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static SuppressionSetRequest fromMap(Map<String, Object> data) {
        SuppressionSetRequest result = new SuppressionSetRequest();
        result.email = Json.asString(data.get("email"));
        result.cause = Json.asString(data.get("cause"));
        result.created = Json.asString(data.get("created"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "email", email);
        Json.put(result, "cause", cause);
        Json.put(result, "created", created);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
