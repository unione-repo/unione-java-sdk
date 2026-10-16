package io.unione.api.eventdump.model;

import io.unione.client.Json;
import io.unione.common.model.EmailStatus;
import java.util.LinkedHashMap;
import java.util.Map;

/** An object with the properties of the event dump filter. */
public class FilterObject {
    /**
     * Job identifier returned earlier by [email/send](https://docs.unione.io/en/web-api-ref#email-send)
     * method.
     */
    private String jobId;

    /**
     * Email status. Possible values are:
     * <p>
     * __accepted__ — the message has been accepted, but not sent yet.
     * __sent__ — the message has been sent, but not delivered yet.
     * __delivered__ — the message has been delivered.
     * __opened__ — the message has been delivered and read.
     * __clicked__ — the message has been delivered, read, and at least one of the links in email has been
     * clicked.
     * __unsubscribed__ — the message has been delivered to the recipient and read, but then the recipient
     * unsubscribed.
     * __subscribed__ — the message has been delivered to the recipient and read, but then the recipient
     * unsubscribed and subscribed again.
     * __soft_bounced__ — temporary delivery failure. UniOne will try to deliver the message during 48
     * hours.
     * __hard_bounced__ — failed to deliver the message, there will be no delivery attempts.
     * __spam__ — the message has been delivered, but it was reported as spam by the recipient.
     * <p>
     * Raw value, kept also when the SDK does not know it.
     */
    private String status;

    /**
     * UniOne internal detailed delivery status, you can pass several values at once, comma-separated. Some
     * possible values are:
     * <p>
     * __err_user_unknown__ -- email address doesn't exist;
     * __err_user_inactive__ -- email address isn't used anymore;
     * __err_will_retry__ -- email was temporary rejected, delivery will be retried later;
     * __err_mailbox_discarded__ -- email address was active earlier, but now it's deleted;
     * __err_mailbox_full__ -- mailbox is full;
     * __err_spam_rejected__ -- email was rejected as a spam;
     * __err_blacklisted__ -- email was rejected because sender IP or sender domain is found in a black
     * list;
     * __err_too_large__ -- email size is over limit, according to recipient's server;
     * __err_unsubscribed__ -- email was unsubscribed;
     * __err_unreachable__ -- numerous delivery failures lead to mark this email address as permanently
     * unavailable;
     * __err_skip_letter__ -- sending has been canceled because the email address is temporarily
     * unavailable;
     * __err_domain_inactive__ -- the domain does not accept mail or does not exist;
     * __err_destination_misconfigured__ -- the domain does not accept mail due to incorrect settings on
     * the recipient’s side, and the server’s response contains information about a cause that can be fixed
     * (for example, an inoperative blacklist is used, etc.);
     * __err_delivery_failed__ -- delivery failed due to other reasons;
     * __err_spam_skipped__ -- sending has been canceled because the campaign has been blocked as a spam;
     * __err_lost__ -- the message was not sent due to inconsistency of its parts or it has been lost
     * because of the failure on our side. The sender needs to re-send the letter on his own, since the
     * original has not been saved;
     * <p>
     * We reserve the right to change or add internal delivery statuses as we need, so the following list
     * is provided as an example only, without any description: ok_sent, ok_delivered, ok_read,
     * ok_link_visited, ok_unsubscribed, ok_resubscribed, ok_spam_folder, ok_fbl, not_sent,
     * skip_dup_unreachable, skip_dup_temp_unreachable, skip_dup_mailbox_full, err_spam_removed,
     * err_resend, err_unknown, err_retry_letter, err_src_invalid, err_dest_invalid, err_not_available,
     * err_internal, err_no_dns, err_no_smtp, err_giveup.
     */
    private String deliveryStatus;

    /** Recipient email address */
    private String email;

    /** Sender email address */
    private String emailFrom;

    /** Recipient domain */
    private String domain;

    /**
     * Campaign identifier, unsigned decimal integer or UUID up to 128-bit, passed in metadata with name
     * "campaign_id" (name is configurable thru support).
     */
    private String campaignId;

    public String getJobId() {
        return jobId;
    }

    public FilterObject setJobId(String jobId) {
        this.jobId = jobId;
        return this;
    }

    /**
     * Enum value of the field, UNKNOWN_VALUE for a value unknown to this SDK version (the raw one:
     * getStatusValue()), null when absent.
     */
    public EmailStatus getStatus() {
        return EmailStatus.fromValue(status);
    }

    /**
     * Sets the field; IllegalArgumentException for UNKNOWN_VALUE (an unknown value goes to
     * setStatusValue()).
     */
    public FilterObject setStatus(EmailStatus status) {
        this.status = EmailStatus.toValue(status);
        return this;
    }

    /** Raw value of the field as sent by the API, also when the SDK does not know it. */
    public String getStatusValue() {
        return status;
    }

    /** Sets the raw value, e.g. one added to the API after this SDK version. */
    public FilterObject setStatusValue(String status) {
        this.status = status;
        return this;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public FilterObject setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public FilterObject setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public FilterObject setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public FilterObject setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public FilterObject setCampaignId(String campaignId) {
        this.campaignId = campaignId;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static FilterObject fromMap(Map<String, Object> data) {
        FilterObject result = new FilterObject();
        result.jobId = Json.asString(data.get("job_id"));
        result.status = Json.asString(data.get("status"));
        result.deliveryStatus = Json.asString(data.get("delivery_status"));
        result.email = Json.asString(data.get("email"));
        result.emailFrom = Json.asString(data.get("email_from"));
        result.domain = Json.asString(data.get("domain"));
        result.campaignId = Json.asString(data.get("campaign_id"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "job_id", jobId);
        Json.put(result, "status", status);
        Json.put(result, "delivery_status", deliveryStatus);
        Json.put(result, "email", email);
        Json.put(result, "email_from", emailFrom);
        Json.put(result, "domain", domain);
        Json.put(result, "campaign_id", campaignId);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
