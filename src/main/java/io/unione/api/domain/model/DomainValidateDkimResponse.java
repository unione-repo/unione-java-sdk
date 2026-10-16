package io.unione.api.domain.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** DomainValidateDkimResponse */
public class DomainValidateDkimResponse {
    /**
     * "success" string
     * Allowed values: `success`.
     */
    private String status;

    /** Debug message. */
    private String message;

    public String getStatus() {
        return status;
    }

    public DomainValidateDkimResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public DomainValidateDkimResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static DomainValidateDkimResponse fromMap(Map<String, Object> data) {
        DomainValidateDkimResponse result = new DomainValidateDkimResponse();
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
