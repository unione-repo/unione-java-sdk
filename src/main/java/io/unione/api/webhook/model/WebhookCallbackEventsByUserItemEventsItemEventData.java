package io.unione.api.webhook.model;

import io.unione.client.Json;
import io.unione.common.model.EmailStatus;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Object with different event properties depending on "event_name". Below you can see all the
 * properties, "transactional_email_status"-related first and then "transactional_spam_block"-related.
 */
public class WebhookCallbackEventsByUserItemEventsItemEventData {
    /**
     * Job identifier returned earlier by [email/send](https://docs.unione.io/en/web-api-ref#email-send)
     * method. Property exists only if event_name="transactional_email_status".
     */
    private String jobId;

    /**
     * Metadata passed in [email/send](https://docs.unione.io/en/web-api-ref#email-send) method in
     * recipients.metadata or global_metadata properties. This property exists only if
     * event_name="transactional_email_status".
     */
    private Map<String, String> metadata;

    /** Recipient's email. This property exists only if event_name="transactional_email_status". */
    private String email;

    /**
     * Email delivery status. This property exists only if event_name="transactional_email_status".
     * Possible values are:
     * <p>
     * __accepted__ — the message has been accepted, but not sent yet.
     * __sent__ — the message has been sent, but not delivered yet.
     * __delivered__ — the message has been delivered. It can be changed to “opened”, “clicked”,
     * “unsubscribed”, “subscribed” or “spam”.
     * __opened__ — the message has been delivered and read. It can be changed to “clicked”,
     * “unsubscribed”, “subscribed” or “spam”.
     * __clicked__ — the message has been delivered, read, and at least one of the links in email has been
     * clicked. It can be changed to “unsubscribed”, “subscribed” or “spam”.
     * __unsubscribed__ — the message has been delivered to the recipient and read, but then the recipient
     * unsubscribed. It can be changed to “subscribed”.
     * __subscribed__ — the message has been delivered to the recipient and read, but then the recipient
     * unsubscribed and subscribed again. It can be changed to “unsubscribed”.
     * __soft_bounced__ — temporary delivery failure. UniOne will try to deliver the message during 48
     * hours. In case of success the status changes to “delivered”, in case of failure to deliver during 48
     * hours message status changes to “hard_bounced”.
     * __hard_bounced__ — failed to deliver the message, there will be no delivery attempts. It's a final
     * status. There are a lot of possible reasons, you can use SMTP response from
     * delivery_info.destination_response field or UniOne internal reason classification from
     * delivery_info.delivery_status field to analyze the reason.
     * __spam__ — the message has been delivered, but it was reported as spam by the recipient. UniOne can
     * receive and process the spam complaint from several domains, including msn.com, outlook.com,
     * hotmail.com, live.com, ukr.net, yahoo.com, aol.com using
     * [FBL](https://en.wikipedia.org/wiki/Feedback_loop_(email)).
     * <p>
     * Raw value, kept also when the SDK does not know it.
     */
    private String status;

    /**
     * Event date &amp; time in UTC time zone in "YYYY-MM-DD hh:mm:ss" format. This property exists only if
     * event_name="transactional_email_status".
     */
    private String eventTime;

    /**
     * URL for "opened" and "clicked" statuses. This property exists only if
     * event_name="transactional_email_status".
     */
    private String url;

    /**
     * Object with detailed delivery info.Event date &amp; time in UTC time zone in "YYYY-MM-DD hh:mm:ss"
     * format. This property exists only if webhook has delivery_info proeprty set to 1 and
     * event_name="transactional_email_status".
     */
    private WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo deliveryInfo;

    /**
     * Spam block date &amp; time in UTC time zone in "YYYY-MM-DD hh:mm:ss" format. This property exists only
     * if event_name="transactional_spam_block".
     */
    private String blockTime;

    /**
     * Spam block type, either single or multiple sending SMTP. For single sending SMTP block in common
     * pool UniOne retries several other SMTPs. This property exists only if
     * event_name="transactional_spam_block".
     * <p>
     * Raw value, kept also when the SDK does not know it.
     */
    private String blockType;

    /** Domain that blocked sending. This property exists only if event_name="transactional_spam_block". */
    private String domain;

    /** Number of sending SMTPs blocked. This property exists only if event_name="transactional_spam_block". */
    private Long sMTPBlocksCount;

    /**
     * Whether it's a block or unblock event. This property exists only if
     * event_name="transactional_spam_block".
     * <p>
     * Raw value, kept also when the SDK does not know it.
     */
    private String domainStatus;

    public String getJobId() {
        return jobId;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventData setJobId(String jobId) {
        this.jobId = jobId;
        return this;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventData setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventData setEmail(String email) {
        this.email = email;
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
    public WebhookCallbackEventsByUserItemEventsItemEventData setStatus(EmailStatus status) {
        this.status = EmailStatus.toValue(status);
        return this;
    }

    /** Raw value of the field as sent by the API, also when the SDK does not know it. */
    public String getStatusValue() {
        return status;
    }

    /** Sets the raw value, e.g. one added to the API after this SDK version. */
    public WebhookCallbackEventsByUserItemEventsItemEventData setStatusValue(String status) {
        this.status = status;
        return this;
    }

    public String getEventTime() {
        return eventTime;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventData setEventTime(String eventTime) {
        this.eventTime = eventTime;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventData setUrl(String url) {
        this.url = url;
        return this;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventData setDeliveryInfo(WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
        return this;
    }

    public String getBlockTime() {
        return blockTime;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventData setBlockTime(String blockTime) {
        this.blockTime = blockTime;
        return this;
    }

    /**
     * Enum value of the field, UNKNOWN_VALUE for a value unknown to this SDK version (the raw one:
     * getBlockTypeValue()), null when absent.
     */
    public BlockType getBlockType() {
        return BlockType.fromValue(blockType);
    }

    /**
     * Sets the field; IllegalArgumentException for UNKNOWN_VALUE (an unknown value goes to
     * setBlockTypeValue()).
     */
    public WebhookCallbackEventsByUserItemEventsItemEventData setBlockType(BlockType blockType) {
        this.blockType = BlockType.toValue(blockType);
        return this;
    }

    /** Raw value of the field as sent by the API, also when the SDK does not know it. */
    public String getBlockTypeValue() {
        return blockType;
    }

    /** Sets the raw value, e.g. one added to the API after this SDK version. */
    public WebhookCallbackEventsByUserItemEventsItemEventData setBlockTypeValue(String blockType) {
        this.blockType = blockType;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventData setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public Long getSMTPBlocksCount() {
        return sMTPBlocksCount;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventData setSMTPBlocksCount(Long sMTPBlocksCount) {
        this.sMTPBlocksCount = sMTPBlocksCount;
        return this;
    }

    /**
     * Enum value of the field, UNKNOWN_VALUE for a value unknown to this SDK version (the raw one:
     * getDomainStatusValue()), null when absent.
     */
    public DomainStatus getDomainStatus() {
        return DomainStatus.fromValue(domainStatus);
    }

    /**
     * Sets the field; IllegalArgumentException for UNKNOWN_VALUE (an unknown value goes to
     * setDomainStatusValue()).
     */
    public WebhookCallbackEventsByUserItemEventsItemEventData setDomainStatus(DomainStatus domainStatus) {
        this.domainStatus = DomainStatus.toValue(domainStatus);
        return this;
    }

    /** Raw value of the field as sent by the API, also when the SDK does not know it. */
    public String getDomainStatusValue() {
        return domainStatus;
    }

    /** Sets the raw value, e.g. one added to the API after this SDK version. */
    public WebhookCallbackEventsByUserItemEventsItemEventData setDomainStatusValue(String domainStatus) {
        this.domainStatus = domainStatus;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static WebhookCallbackEventsByUserItemEventsItemEventData fromMap(Map<String, Object> data) {
        WebhookCallbackEventsByUserItemEventsItemEventData result = new WebhookCallbackEventsByUserItemEventsItemEventData();
        result.jobId = Json.asString(data.get("job_id"));
        result.metadata = Json.asMapOf(data.get("metadata"), Json::asString);
        result.email = Json.asString(data.get("email"));
        result.status = Json.asString(data.get("status"));
        result.eventTime = Json.asString(data.get("event_time"));
        result.url = Json.asString(data.get("url"));
        result.deliveryInfo = Json.asObject(data.get("delivery_info"), WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo::fromMap);
        result.blockTime = Json.asString(data.get("block_time"));
        result.blockType = Json.asString(data.get("block_type"));
        result.domain = Json.asString(data.get("domain"));
        result.sMTPBlocksCount = Json.asLong(data.get("SMTP_blocks_count"));
        result.domainStatus = Json.asString(data.get("domain_status"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "job_id", jobId);
        Json.put(result, "metadata", metadata);
        Json.put(result, "email", email);
        Json.put(result, "status", status);
        Json.put(result, "event_time", eventTime);
        Json.put(result, "url", url);
        Json.put(result, "delivery_info", deliveryInfo == null ? null : deliveryInfo.toMap());
        Json.put(result, "block_time", blockTime);
        Json.put(result, "block_type", blockType);
        Json.put(result, "domain", domain);
        Json.put(result, "SMTP_blocks_count", sMTPBlocksCount);
        Json.put(result, "domain_status", domainStatus);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
