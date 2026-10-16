package io.unione.api.domain.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** DomainDeleteRequest */
public class DomainDeleteRequest {
    /** Domain name to delete. */
    private String domain;

    public String getDomain() {
        return domain;
    }

    public DomainDeleteRequest setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static DomainDeleteRequest fromMap(Map<String, Object> data) {
        DomainDeleteRequest result = new DomainDeleteRequest();
        result.domain = Json.asString(data.get("domain"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "domain", domain);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
