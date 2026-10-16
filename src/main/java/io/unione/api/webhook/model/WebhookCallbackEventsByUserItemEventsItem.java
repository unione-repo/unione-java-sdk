package io.unione.api.webhook.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** WebhookCallbackEventsByUserItemEventsItem */
public class WebhookCallbackEventsByUserItemEventsItem {
    /**
     * Type of event data contained in event_data field, either "transactional_email_status" or
     * "transactional_spam_block".
     * <p>
     * Raw value, kept also when the SDK does not know it.
     */
    private String eventName;

    /**
     * Object with different event properties depending on "event_name". Below you can see all the
     * properties, "transactional_email_status"-related first and then "transactional_spam_block"-related.
     */
    private WebhookCallbackEventsByUserItemEventsItemEventData eventData;

    /**
     * Enum value of the field, UNKNOWN_VALUE for a value unknown to this SDK version (the raw one:
     * getEventNameValue()), null when absent.
     */
    public EventName getEventName() {
        return EventName.fromValue(eventName);
    }

    /**
     * Sets the field; IllegalArgumentException for UNKNOWN_VALUE (an unknown value goes to
     * setEventNameValue()).
     */
    public WebhookCallbackEventsByUserItemEventsItem setEventName(EventName eventName) {
        this.eventName = EventName.toValue(eventName);
        return this;
    }

    /** Raw value of the field as sent by the API, also when the SDK does not know it. */
    public String getEventNameValue() {
        return eventName;
    }

    /** Sets the raw value, e.g. one added to the API after this SDK version. */
    public WebhookCallbackEventsByUserItemEventsItem setEventNameValue(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public WebhookCallbackEventsByUserItemEventsItemEventData getEventData() {
        return eventData;
    }

    public WebhookCallbackEventsByUserItemEventsItem setEventData(WebhookCallbackEventsByUserItemEventsItemEventData eventData) {
        this.eventData = eventData;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static WebhookCallbackEventsByUserItemEventsItem fromMap(Map<String, Object> data) {
        WebhookCallbackEventsByUserItemEventsItem result = new WebhookCallbackEventsByUserItemEventsItem();
        result.eventName = Json.asString(data.get("event_name"));
        result.eventData = Json.asObject(data.get("event_data"), WebhookCallbackEventsByUserItemEventsItemEventData::fromMap);
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "event_name", eventName);
        Json.put(result, "event_data", eventData == null ? null : eventData.toMap());
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
