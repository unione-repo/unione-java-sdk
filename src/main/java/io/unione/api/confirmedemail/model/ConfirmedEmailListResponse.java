package io.unione.api.confirmedemail.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** ConfirmedEmailListResponse */
public class ConfirmedEmailListResponse {
    /**
     * "success" string
     * Allowed values: `success`.
     */
    private String status;

    private List<ConfirmedEmail> confirmedEmails;

    public String getStatus() {
        return status;
    }

    public ConfirmedEmailListResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public List<ConfirmedEmail> getConfirmedEmails() {
        return confirmedEmails;
    }

    public ConfirmedEmailListResponse setConfirmedEmails(List<ConfirmedEmail> confirmedEmails) {
        this.confirmedEmails = confirmedEmails;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static ConfirmedEmailListResponse fromMap(Map<String, Object> data) {
        ConfirmedEmailListResponse result = new ConfirmedEmailListResponse();
        result.status = Json.asString(data.get("status"));
        result.confirmedEmails = Json.asList(data.get("confirmed_emails"), v0 -> Json.asObject(v0, ConfirmedEmail::fromMap));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "status", status);
        Json.put(result, "confirmed_emails", Json.mapList(confirmedEmails, v0 -> v0.toMap()));
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
