package io.unione.api.confirmedemail.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** ConfirmedEmailRequest */
public class ConfirmedEmailRequest {
    /** Email address to add or delete. */
    private String email;

    public String getEmail() {
        return email;
    }

    public ConfirmedEmailRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static ConfirmedEmailRequest fromMap(Map<String, Object> data) {
        ConfirmedEmailRequest result = new ConfirmedEmailRequest();
        result.email = Json.asString(data.get("email"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "email", email);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
