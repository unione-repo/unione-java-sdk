package io.unione.api.confirmedemail.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** ConfirmedEmailCreateResponse */
public class ConfirmedEmailCreateResponse {
    /**
     * "success" string
     * Allowed values: `success`.
     */
    private String status;

    private ConfirmedEmail confirmedEmail;

    public String getStatus() {
        return status;
    }

    public ConfirmedEmailCreateResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public ConfirmedEmail getConfirmedEmail() {
        return confirmedEmail;
    }

    public ConfirmedEmailCreateResponse setConfirmedEmail(ConfirmedEmail confirmedEmail) {
        this.confirmedEmail = confirmedEmail;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static ConfirmedEmailCreateResponse fromMap(Map<String, Object> data) {
        ConfirmedEmailCreateResponse result = new ConfirmedEmailCreateResponse();
        result.status = Json.asString(data.get("status"));
        result.confirmedEmail = Json.asObject(data.get("confirmed_email"), ConfirmedEmail::fromMap);
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "status", status);
        Json.put(result, "confirmed_email", confirmedEmail == null ? null : confirmedEmail.toMap());
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
