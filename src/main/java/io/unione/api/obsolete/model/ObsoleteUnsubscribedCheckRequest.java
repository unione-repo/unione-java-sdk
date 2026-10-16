package io.unione.api.obsolete.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** ObsoleteUnsubscribedCheckRequest */
public class ObsoleteUnsubscribedCheckRequest {
    /** Email to check. */
    private String address;

    public String getAddress() {
        return address;
    }

    public ObsoleteUnsubscribedCheckRequest setAddress(String address) {
        this.address = address;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static ObsoleteUnsubscribedCheckRequest fromMap(Map<String, Object> data) {
        ObsoleteUnsubscribedCheckRequest result = new ObsoleteUnsubscribedCheckRequest();
        result.address = Json.asString(data.get("address"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "address", address);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
