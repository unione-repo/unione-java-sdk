package io.unione.api.webhook.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** Object with all the webhook properties. */
public class WebhookObject {
    /** Webhook unique identifier. */
    private Integer id;

    /** Webhook URL. */
    private String url;

    /**
     * Webhook status, "active" by default. "disabled" means that webhook has been disabled by the user,
     * "stopped" means that webhook has been stopped by the system after 24h of failed calls (with minimum
     * of 10 distinct events).
     * Default: `active`.
     * <p>
     * Raw value, kept also when the SDK does not know it.
     */
    private String status;

    /**
     * Notification format. "json_post"(default) or "json_post_gzip".
     * Default: `json_post`.
     * <p>
     * Raw value, kept also when the SDK does not know it.
     */
    private String eventFormat;

    /**
     * Shall detailed delivery info be returned(1) or not(0). When it's 1, the information about SMTP
     * response and internal delivery status is returned for "hard_bounced" and "soft_bounced" email
     * statuses; the user agent, recipient IP address, detected geo fields, device info detected from the
     * user agent (device type and brand, OS name, browser or email client name) and traffic_type (human,
     * machine, protected or unknown) are returned for "opened" and "clicked" statuses; URL is returned for
     * "clicked" status. For "delivered", "soft_bounced" and "hard_bounced" statuses UniOne may also return
     * sender_ip, the IP address of the SMTP server that sent the message, if available.
     * Allowed values: `0`, `1`.
     * Default: `0`.
     */
    private Integer deliveryInfo;

    /**
     * 0=several event notifications can be reported in single webhook call, 1=only single event can be
     * reported in one call (not recommended).
     * Allowed values: `0`, `1`.
     * Default: `0`.
     */
    private Integer singleEvent;

    /**
     * Maximum quantity of permitted parallel queries to your server. The more your server can handle - the
     * better.
     * Default: `10`.
     */
    private Integer maxParallel;

    /** Webhook properties last update date and time in UTC timezone in "YYYY-MM-DD hh:mm:ss" format. */
    private String updatedAt;

    /** Object containing events to notify of. */
    private WebhookEventsObject events;

    public Integer getId() {
        return id;
    }

    public WebhookObject setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public WebhookObject setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * Enum value of the field, UNKNOWN_VALUE for a value unknown to this SDK version (the raw one:
     * getStatusValue()), null when absent.
     */
    public WebhookStatus getStatus() {
        return WebhookStatus.fromValue(status);
    }

    /**
     * Sets the field; IllegalArgumentException for UNKNOWN_VALUE (an unknown value goes to
     * setStatusValue()).
     */
    public WebhookObject setStatus(WebhookStatus status) {
        this.status = WebhookStatus.toValue(status);
        return this;
    }

    /** Raw value of the field as sent by the API, also when the SDK does not know it. */
    public String getStatusValue() {
        return status;
    }

    /** Sets the raw value, e.g. one added to the API after this SDK version. */
    public WebhookObject setStatusValue(String status) {
        this.status = status;
        return this;
    }

    /**
     * Enum value of the field, UNKNOWN_VALUE for a value unknown to this SDK version (the raw one:
     * getEventFormatValue()), null when absent.
     */
    public WebhookEventFormat getEventFormat() {
        return WebhookEventFormat.fromValue(eventFormat);
    }

    /**
     * Sets the field; IllegalArgumentException for UNKNOWN_VALUE (an unknown value goes to
     * setEventFormatValue()).
     */
    public WebhookObject setEventFormat(WebhookEventFormat eventFormat) {
        this.eventFormat = WebhookEventFormat.toValue(eventFormat);
        return this;
    }

    /** Raw value of the field as sent by the API, also when the SDK does not know it. */
    public String getEventFormatValue() {
        return eventFormat;
    }

    /** Sets the raw value, e.g. one added to the API after this SDK version. */
    public WebhookObject setEventFormatValue(String eventFormat) {
        this.eventFormat = eventFormat;
        return this;
    }

    public Integer getDeliveryInfo() {
        return deliveryInfo;
    }

    public WebhookObject setDeliveryInfo(Integer deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
        return this;
    }

    public Integer getSingleEvent() {
        return singleEvent;
    }

    public WebhookObject setSingleEvent(Integer singleEvent) {
        this.singleEvent = singleEvent;
        return this;
    }

    public Integer getMaxParallel() {
        return maxParallel;
    }

    public WebhookObject setMaxParallel(Integer maxParallel) {
        this.maxParallel = maxParallel;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public WebhookObject setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public WebhookEventsObject getEvents() {
        return events;
    }

    public WebhookObject setEvents(WebhookEventsObject events) {
        this.events = events;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static WebhookObject fromMap(Map<String, Object> data) {
        WebhookObject result = new WebhookObject();
        result.id = Json.asInteger(data.get("id"));
        result.url = Json.asString(data.get("url"));
        result.status = Json.asString(data.get("status"));
        result.eventFormat = Json.asString(data.get("event_format"));
        result.deliveryInfo = Json.asInteger(data.get("delivery_info"));
        result.singleEvent = Json.asInteger(data.get("single_event"));
        result.maxParallel = Json.asInteger(data.get("max_parallel"));
        result.updatedAt = Json.asString(data.get("updated_at"));
        result.events = Json.asObject(data.get("events"), WebhookEventsObject::fromMap);
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "id", id);
        Json.put(result, "url", url);
        Json.put(result, "status", status);
        Json.put(result, "event_format", eventFormat);
        Json.put(result, "delivery_info", deliveryInfo);
        Json.put(result, "single_event", singleEvent);
        Json.put(result, "max_parallel", maxParallel);
        Json.put(result, "updated_at", updatedAt);
        Json.put(result, "events", events == null ? null : events.toMap());
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
