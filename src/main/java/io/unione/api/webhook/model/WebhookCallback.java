package io.unione.api.webhook.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Webhook callback format description. */
public class WebhookCallback {
    /**
     * MD5-hash of the message body, in which the value "auth" is replaced by api key of the user/project;
     * with this field the recipient of the notification can both authenticate and verify the notification
     * integrity
     */
    private String auth;

    /** Array with only one element, containing events of a user/project. */
    private List<WebhookCallbackEventsByUserItem> eventsByUser;

    public String getAuth() {
        return auth;
    }

    public WebhookCallback setAuth(String auth) {
        this.auth = auth;
        return this;
    }

    public List<WebhookCallbackEventsByUserItem> getEventsByUser() {
        return eventsByUser;
    }

    public WebhookCallback setEventsByUser(List<WebhookCallbackEventsByUserItem> eventsByUser) {
        this.eventsByUser = eventsByUser;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static WebhookCallback fromMap(Map<String, Object> data) {
        WebhookCallback result = new WebhookCallback();
        result.auth = Json.asString(data.get("auth"));
        result.eventsByUser = Json.asList(data.get("events_by_user"), v0 -> Json.asObject(v0, WebhookCallbackEventsByUserItem::fromMap));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "auth", auth);
        Json.put(result, "events_by_user", Json.mapList(eventsByUser, v0 -> v0.toMap()));
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
