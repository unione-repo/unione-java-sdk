package io.unione.api.domain.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** DomainGetSandboxDomainResponse */
public class DomainGetSandboxDomainResponse {
    /**
     * "success" string
     * Allowed values: `success`.
     */
    private String status;

    /** Sandbox domain name. */
    private String domain;

    public String getStatus() {
        return status;
    }

    public DomainGetSandboxDomainResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public DomainGetSandboxDomainResponse setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static DomainGetSandboxDomainResponse fromMap(Map<String, Object> data) {
        DomainGetSandboxDomainResponse result = new DomainGetSandboxDomainResponse();
        result.status = Json.asString(data.get("status"));
        result.domain = Json.asString(data.get("domain"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "status", status);
        Json.put(result, "domain", domain);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
