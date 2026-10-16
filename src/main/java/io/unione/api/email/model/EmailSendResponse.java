package io.unione.api.email.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** EmailSendResponse */
public class EmailSendResponse {
    /**
     * "success" string
     * Allowed values: `success`.
     */
    private String status;

    /** Job identifier, might be useful for investigating errors. */
    private String jobId;

    /** Array of recipients emails successfully accepted for sending. */
    private List<String> emails;

    /**
     * Object with emails rejected for sending as property names and their statuses as property values,
     * e.g.: {"email1&#64;gmail.com": "temporary_unavailable"}. Possible statuses:
     * <p>
     * - __unsubscribed__ - specified email is unsubscribed;
     * - __invalid__ - the email does not exist or has been entered incorrectly;
     * - __duplicate__ - the email already exists in the request, email duplicating is prevented;
     * - __temporary_unavailable__ - the email address is unavailable. This means that over the next three days sending to this address will return an error. Email may be temporarily unavailable due to several reasons, e.g.:
     * <p>
     * - a previous email has been rejected by the recipient's server for spam;
     * - the recipient's mailbox is full or is not used;
     * - recipient's domain does not accept mail;
     * - sending server was rejected due to blacklisting;
     * <p>
     * - __permanent_unavailable__ - the email address is permanently unavailable or unsubscribed globally.
     * - __complained__ -  the recipient reported spam in the previous emails;
     * - __blocked__ -  sending to the email is prohibited by administration of UniOne.
     * We may added new statuses in the future.
     */
    private Map<String, String> failedEmails;

    public String getStatus() {
        return status;
    }

    public EmailSendResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getJobId() {
        return jobId;
    }

    public EmailSendResponse setJobId(String jobId) {
        this.jobId = jobId;
        return this;
    }

    public List<String> getEmails() {
        return emails;
    }

    public EmailSendResponse setEmails(List<String> emails) {
        this.emails = emails;
        return this;
    }

    public Map<String, String> getFailedEmails() {
        return failedEmails;
    }

    public EmailSendResponse setFailedEmails(Map<String, String> failedEmails) {
        this.failedEmails = failedEmails;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static EmailSendResponse fromMap(Map<String, Object> data) {
        EmailSendResponse result = new EmailSendResponse();
        result.status = Json.asString(data.get("status"));
        result.jobId = Json.asString(data.get("job_id"));
        result.emails = Json.asList(data.get("emails"), Json::asString);
        result.failedEmails = Json.asMapOf(data.get("failed_emails"), Json::asString);
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "status", status);
        Json.put(result, "job_id", jobId);
        Json.put(result, "emails", emails);
        Json.put(result, "failed_emails", failedEmails);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
