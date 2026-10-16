package io.unione.api.domain.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** Object describing DKIM record value and status. */
public class DomainListResponseDomainsItemDkim {
    /**
     * DKIM signature for the domain. This property contains only the key part, you should prepend it with
     * "k=rsa, p=" part for the record to be valid (see
     * [domain/get-dns-records](https://docs.unione.io/en/web-api-ref#domain-get-dns-records)).
     */
    private String key;

    /**
     * Only domains with "active" DKIM record are allowed as sender domains.
     * <p>
     * Raw value, kept also when the SDK does not know it.
     */
    private String status;

    public String getKey() {
        return key;
    }

    public DomainListResponseDomainsItemDkim setKey(String key) {
        this.key = key;
        return this;
    }

    /**
     * Enum value of the field, UNKNOWN_VALUE for a value unknown to this SDK version (the raw one:
     * getStatusValue()), null when absent.
     */
    public DkimStatus getStatus() {
        return DkimStatus.fromValue(status);
    }

    /**
     * Sets the field; IllegalArgumentException for UNKNOWN_VALUE (an unknown value goes to
     * setStatusValue()).
     */
    public DomainListResponseDomainsItemDkim setStatus(DkimStatus status) {
        this.status = DkimStatus.toValue(status);
        return this;
    }

    /** Raw value of the field as sent by the API, also when the SDK does not know it. */
    public String getStatusValue() {
        return status;
    }

    /** Sets the raw value, e.g. one added to the API after this SDK version. */
    public DomainListResponseDomainsItemDkim setStatusValue(String status) {
        this.status = status;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static DomainListResponseDomainsItemDkim fromMap(Map<String, Object> data) {
        DomainListResponseDomainsItemDkim result = new DomainListResponseDomainsItemDkim();
        result.key = Json.asString(data.get("key"));
        result.status = Json.asString(data.get("status"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "key", key);
        Json.put(result, "status", status);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
