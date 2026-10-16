package io.unione.api.domain.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** DomainListRequest */
public class DomainListRequest {
    /** Optional domain name if you want to get the status of single domain only. */
    private String domain;

    /**
     * Limits the number of domains returned. Default is 50.
     * Default: `50`.
     */
    private Integer limit;

    /**
     * Index of the first domain in the list, starting with 0.
     * Default: `0`.
     */
    private Integer offset;

    public String getDomain() {
        return domain;
    }

    public DomainListRequest setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public DomainListRequest setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public DomainListRequest setOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static DomainListRequest fromMap(Map<String, Object> data) {
        DomainListRequest result = new DomainListRequest();
        result.domain = Json.asString(data.get("domain"));
        result.limit = Json.asInteger(data.get("limit"));
        result.offset = Json.asInteger(data.get("offset"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "domain", domain);
        Json.put(result, "limit", limit);
        Json.put(result, "offset", offset);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
