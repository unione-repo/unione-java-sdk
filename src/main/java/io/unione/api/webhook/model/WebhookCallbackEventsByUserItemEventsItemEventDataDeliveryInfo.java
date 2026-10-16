package io.unione.api.webhook.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Object with detailed delivery info.Event date &amp; time in UTC time zone in "YYYY-MM-DD hh:mm:ss"
 * format. This property exists only if webhook has delivery_info proeprty set to 1 and
 * event_name="transactional_email_status".
 */
public class WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo {
    /**
     * UniOne internal detailed delivery status. Some possible values are:
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

    /** SMTP response. */
    private String destinationResponse;

    /** User agent of recipient. Present only if detected for "clicked" and "opened" statuses. */
    private String userAgent;

    /**
     * Recipient's device type, detected from the user agent. Present for "clicked" and "opened" statuses;
     * equals "unknown" if the device type couldn't be detected.
     * <p>
     * Raw value, kept also when the SDK does not know it.
     */
    private String deviceType;

    /**
     * Recipient's device brand, detected from the user agent. Present for "clicked" and "opened" statuses
     * if detected. For device_type "mobile" and "tablet" it's always present and equals "unknown" when the
     * brand couldn't be detected.
     */
    private String deviceBrand;

    /**
     * Name of the operating system of the recipient's device, detected from the user agent. Present for
     * "clicked" and "opened" statuses; equals "unknown" if the OS couldn't be detected.
     */
    private String osName;

    /**
     * Name of the browser the email was opened in, detected from the user agent. Present for "clicked" and
     * "opened" statuses only if a browser was detected, so it never comes together with email_client_name.
     */
    private String browserName;

    /**
     * Name of the email client application the email was opened in, detected from the user agent. Present
     * for "clicked" and "opened" statuses only if an email client was detected, so it never comes together
     * with browser_name.
     */
    private String emailClientName;

    /**
     * Traffic quality for "opened" and "clicked" statuses. "human" means a recognized browser or email
     * client without machine signs; "machine" means a bot, link scanner or security proxy; "protected"
     * means a mailbox/OS privacy proxy where a human action cannot be confirmed (Apple Mail Privacy
     * Protection / iCloud Private Relay, Gmail Image Proxy — Apple MPP is protected, not machine);
     * "unknown" means the user agent is missing, broken or not recognized.
     * <p>
     * Raw value, kept also when the SDK does not know it.
     */
    private String trafficType;

    /** Recipient's IP address. Present only if detected for "clicked" and "opened" statuses. */
    private String ip;

    /**
     * ISO 3166-1 alpha-2 country code detected from recipient IP. Present for "clicked" and "opened"
     * statuses.
     */
    private String countryCode;

    /** Country detected from recipient IP. Present for "clicked" and "opened" statuses. */
    private String country;

    /** City detected from recipient IP. Present for "clicked" and "opened" statuses. */
    private String city;

    /**
     * IP address of the SMTP server that sent the message. Present only for "delivered", "soft_bounced"
     * and "hard_bounced" statuses, if available.
     */
    private String senderIp;

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        return this;
    }

    public String getDestinationResponse() {
        return destinationResponse;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo setDestinationResponse(String destinationResponse) {
        this.destinationResponse = destinationResponse;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    /**
     * Enum value of the field, UNKNOWN_VALUE for a value unknown to this SDK version (the raw one:
     * getDeviceTypeValue()), null when absent.
     */
    public DeviceType getDeviceType() {
        return DeviceType.fromValue(deviceType);
    }

    /**
     * Sets the field; IllegalArgumentException for UNKNOWN_VALUE (an unknown value goes to
     * setDeviceTypeValue()).
     */
    public WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo setDeviceType(DeviceType deviceType) {
        this.deviceType = DeviceType.toValue(deviceType);
        return this;
    }

    /** Raw value of the field as sent by the API, also when the SDK does not know it. */
    public String getDeviceTypeValue() {
        return deviceType;
    }

    /** Sets the raw value, e.g. one added to the API after this SDK version. */
    public WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo setDeviceTypeValue(String deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
        return this;
    }

    public String getOsName() {
        return osName;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo setOsName(String osName) {
        this.osName = osName;
        return this;
    }

    public String getBrowserName() {
        return browserName;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo setBrowserName(String browserName) {
        this.browserName = browserName;
        return this;
    }

    public String getEmailClientName() {
        return emailClientName;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo setEmailClientName(String emailClientName) {
        this.emailClientName = emailClientName;
        return this;
    }

    /**
     * Enum value of the field, UNKNOWN_VALUE for a value unknown to this SDK version (the raw one:
     * getTrafficTypeValue()), null when absent.
     */
    public TrafficType getTrafficType() {
        return TrafficType.fromValue(trafficType);
    }

    /**
     * Sets the field; IllegalArgumentException for UNKNOWN_VALUE (an unknown value goes to
     * setTrafficTypeValue()).
     */
    public WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo setTrafficType(TrafficType trafficType) {
        this.trafficType = TrafficType.toValue(trafficType);
        return this;
    }

    /** Raw value of the field as sent by the API, also when the SDK does not know it. */
    public String getTrafficTypeValue() {
        return trafficType;
    }

    /** Sets the raw value, e.g. one added to the API after this SDK version. */
    public WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo setTrafficTypeValue(String trafficType) {
        this.trafficType = trafficType;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo setCity(String city) {
        this.city = city;
        return this;
    }

    public String getSenderIp() {
        return senderIp;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo setSenderIp(String senderIp) {
        this.senderIp = senderIp;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo fromMap(Map<String, Object> data) {
        WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo result = new WebhookCallbackEventsByUserItemEventsItemEventDataDeliveryInfo();
        result.deliveryStatus = Json.asString(data.get("delivery_status"));
        result.destinationResponse = Json.asString(data.get("destination_response"));
        result.userAgent = Json.asString(data.get("user_agent"));
        result.deviceType = Json.asString(data.get("device_type"));
        result.deviceBrand = Json.asString(data.get("device_brand"));
        result.osName = Json.asString(data.get("os_name"));
        result.browserName = Json.asString(data.get("browser_name"));
        result.emailClientName = Json.asString(data.get("email_client_name"));
        result.trafficType = Json.asString(data.get("traffic_type"));
        result.ip = Json.asString(data.get("ip"));
        result.countryCode = Json.asString(data.get("country_code"));
        result.country = Json.asString(data.get("country"));
        result.city = Json.asString(data.get("city"));
        result.senderIp = Json.asString(data.get("sender_ip"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "delivery_status", deliveryStatus);
        Json.put(result, "destination_response", destinationResponse);
        Json.put(result, "user_agent", userAgent);
        Json.put(result, "device_type", deviceType);
        Json.put(result, "device_brand", deviceBrand);
        Json.put(result, "os_name", osName);
        Json.put(result, "browser_name", browserName);
        Json.put(result, "email_client_name", emailClientName);
        Json.put(result, "traffic_type", trafficType);
        Json.put(result, "ip", ip);
        Json.put(result, "country_code", countryCode);
        Json.put(result, "country", country);
        Json.put(result, "city", city);
        Json.put(result, "sender_ip", senderIp);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
