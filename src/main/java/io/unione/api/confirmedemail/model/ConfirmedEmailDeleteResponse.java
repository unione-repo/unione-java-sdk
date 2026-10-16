package io.unione.api.confirmedemail.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** ConfirmedEmailDeleteResponse */
public class ConfirmedEmailDeleteResponse {
    /**
     * "success" string
     * Allowed values: `success`.
     */
    private String status;

    private String message;

    public String getStatus() {
        return status;
    }

    public ConfirmedEmailDeleteResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ConfirmedEmailDeleteResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static ConfirmedEmailDeleteResponse fromMap(Map<String, Object> data) {
        ConfirmedEmailDeleteResponse result = new ConfirmedEmailDeleteResponse();
        result.status = Json.asString(data.get("status"));
        result.message = Json.asString(data.get("message"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "status", status);
        Json.put(result, "message", message);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
