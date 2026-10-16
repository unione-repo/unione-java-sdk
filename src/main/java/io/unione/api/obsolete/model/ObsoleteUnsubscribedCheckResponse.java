package io.unione.api.obsolete.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** ObsoleteUnsubscribedCheckResponse */
public class ObsoleteUnsubscribedCheckResponse {
    /**
     * "success" string
     * Allowed values: `success`.
     */
    private String status;

    /** Email that was checked. */
    private String address;

    /** true if unsubscribed, false if not. */
    private Boolean isUnsubscribed;

    public String getStatus() {
        return status;
    }

    public ObsoleteUnsubscribedCheckResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ObsoleteUnsubscribedCheckResponse setAddress(String address) {
        this.address = address;
        return this;
    }

    public Boolean getIsUnsubscribed() {
        return isUnsubscribed;
    }

    public ObsoleteUnsubscribedCheckResponse setIsUnsubscribed(Boolean isUnsubscribed) {
        this.isUnsubscribed = isUnsubscribed;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static ObsoleteUnsubscribedCheckResponse fromMap(Map<String, Object> data) {
        ObsoleteUnsubscribedCheckResponse result = new ObsoleteUnsubscribedCheckResponse();
        result.status = Json.asString(data.get("status"));
        result.address = Json.asString(data.get("address"));
        result.isUnsubscribed = Json.asBoolean(data.get("is_unsubscribed"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "status", status);
        Json.put(result, "address", address);
        Json.put(result, "is_unsubscribed", isUnsubscribed);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
