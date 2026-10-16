package io.unione.api.domain.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** DomainValidateDkimRequest */
public class DomainValidateDkimRequest {
    /** Domain to validate DKIM record for. */
    private String domain;

    public String getDomain() {
        return domain;
    }

    public DomainValidateDkimRequest setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static DomainValidateDkimRequest fromMap(Map<String, Object> data) {
        DomainValidateDkimRequest result = new DomainValidateDkimRequest();
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
