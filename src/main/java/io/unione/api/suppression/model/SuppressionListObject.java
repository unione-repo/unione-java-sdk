package io.unione.api.suppression.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** Object with suppression details. */
public class SuppressionListObject {
    /** Email to get suppression details for. */
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

    /** Is it possible to delete this suppression by calling suppression/delete method. */
    private Boolean isDeletable;

    /** When suppression was created, in UTC timezone in "YYYY-MM-DD hh:mm:ss" format. */
    private String created;

    public String getEmail() {
        return email;
    }

    public SuppressionListObject setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCause() {
        return cause;
    }

    public SuppressionListObject setCause(String cause) {
        this.cause = cause;
        return this;
    }

    public String getSource() {
        return source;
    }

    public SuppressionListObject setSource(String source) {
        this.source = source;
        return this;
    }

    public Boolean getIsDeletable() {
        return isDeletable;
    }

    public SuppressionListObject setIsDeletable(Boolean isDeletable) {
        this.isDeletable = isDeletable;
        return this;
    }

    public String getCreated() {
        return created;
    }

    public SuppressionListObject setCreated(String created) {
        this.created = created;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static SuppressionListObject fromMap(Map<String, Object> data) {
        SuppressionListObject result = new SuppressionListObject();
        result.email = Json.asString(data.get("email"));
        result.cause = Json.asString(data.get("cause"));
        result.source = Json.asString(data.get("source"));
        result.isDeletable = Json.asBoolean(data.get("is_deletable"));
        result.created = Json.asString(data.get("created"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "email", email);
        Json.put(result, "cause", cause);
        Json.put(result, "source", source);
        Json.put(result, "is_deletable", isDeletable);
        Json.put(result, "created", created);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
