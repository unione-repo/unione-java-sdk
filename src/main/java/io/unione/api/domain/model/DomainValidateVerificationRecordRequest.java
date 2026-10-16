package io.unione.api.domain.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** DomainValidateVerificationRecordRequest */
public class DomainValidateVerificationRecordRequest {
    /** Domain to validate verification record for. */
    private String domain;

    public String getDomain() {
        return domain;
    }

    public DomainValidateVerificationRecordRequest setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static DomainValidateVerificationRecordRequest fromMap(Map<String, Object> data) {
        DomainValidateVerificationRecordRequest result = new DomainValidateVerificationRecordRequest();
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
