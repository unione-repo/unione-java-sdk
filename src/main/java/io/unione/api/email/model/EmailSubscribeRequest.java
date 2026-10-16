package io.unione.api.email.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** EmailSubscribeRequest */
public class EmailSubscribeRequest {
    /** Sender’s email on a confirmed domain. (Field alias "email_address_from" is supported, too). */
    private String fromEmail;

    /** Sender’s name. (Field alias "name_from" is supported, too). */
    private String fromName;

    /** Recipient’s email. (Field alias "email_address_to" is supported, too). */
    private String toEmail;

    public String getFromEmail() {
        return fromEmail;
    }

    public EmailSubscribeRequest setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
        return this;
    }

    public String getFromName() {
        return fromName;
    }

    public EmailSubscribeRequest setFromName(String fromName) {
        this.fromName = fromName;
        return this;
    }

    public String getToEmail() {
        return toEmail;
    }

    public EmailSubscribeRequest setToEmail(String toEmail) {
        this.toEmail = toEmail;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static EmailSubscribeRequest fromMap(Map<String, Object> data) {
        EmailSubscribeRequest result = new EmailSubscribeRequest();
        result.fromEmail = Json.asString(data.get("from_email"));
        result.fromName = Json.asString(data.get("from_name"));
        result.toEmail = Json.asString(data.get("to_email"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "from_email", fromEmail);
        Json.put(result, "from_name", fromName);
        Json.put(result, "to_email", toEmail);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
