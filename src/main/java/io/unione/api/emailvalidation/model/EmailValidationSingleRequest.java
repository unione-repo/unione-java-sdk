package io.unione.api.emailvalidation.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** EmailValidationSingleRequest */
public class EmailValidationSingleRequest {
    /** Email address to be checked. */
    private String email;

    public String getEmail() {
        return email;
    }

    public EmailValidationSingleRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static EmailValidationSingleRequest fromMap(Map<String, Object> data) {
        EmailValidationSingleRequest result = new EmailValidationSingleRequest();
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
