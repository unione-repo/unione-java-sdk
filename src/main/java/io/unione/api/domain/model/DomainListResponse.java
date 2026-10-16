package io.unione.api.domain.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** DomainListResponse */
public class DomainListResponse {
    /**
     * "success" string
     * Allowed values: `success`.
     */
    private String status;

    /** Array of objects describing domains. */
    private List<DomainListResponseDomainsItem> domains;

    public String getStatus() {
        return status;
    }

    public DomainListResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public List<DomainListResponseDomainsItem> getDomains() {
        return domains;
    }

    public DomainListResponse setDomains(List<DomainListResponseDomainsItem> domains) {
        this.domains = domains;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static DomainListResponse fromMap(Map<String, Object> data) {
        DomainListResponse result = new DomainListResponse();
        result.status = Json.asString(data.get("status"));
        result.domains = Json.asList(data.get("domains"), v0 -> Json.asObject(v0, DomainListResponseDomainsItem::fromMap));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "status", status);
        Json.put(result, "domains", Json.mapList(domains, v0 -> v0.toMap()));
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
