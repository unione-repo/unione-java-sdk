package io.unione.api.email.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** EmailSendRequest */
public class EmailSendRequest {
    /** Object containing all message properties. */
    private MessageObject message;

    public MessageObject getMessage() {
        return message;
    }

    public EmailSendRequest setMessage(MessageObject message) {
        this.message = message;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static EmailSendRequest fromMap(Map<String, Object> data) {
        EmailSendRequest result = new EmailSendRequest();
        result.message = Json.asObject(data.get("message"), MessageObject::fromMap);
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "message", message == null ? null : message.toMap());
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
