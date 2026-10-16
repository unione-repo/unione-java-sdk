package io.unione.api.domain.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** DomainListResponseDomainsItem */
public class DomainListResponseDomainsItem {
    /** Domain name. */
    private String domain;

    /** Object describing verification record value and status. */
    private DomainListResponseDomainsItemVerificationRecord verificationRecord;

    /** Object describing DKIM record value and status. */
    private DomainListResponseDomainsItemDkim dkim;

    public String getDomain() {
        return domain;
    }

    public DomainListResponseDomainsItem setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public DomainListResponseDomainsItemVerificationRecord getVerificationRecord() {
        return verificationRecord;
    }

    public DomainListResponseDomainsItem setVerificationRecord(DomainListResponseDomainsItemVerificationRecord verificationRecord) {
        this.verificationRecord = verificationRecord;
        return this;
    }

    public DomainListResponseDomainsItemDkim getDkim() {
        return dkim;
    }

    public DomainListResponseDomainsItem setDkim(DomainListResponseDomainsItemDkim dkim) {
        this.dkim = dkim;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static DomainListResponseDomainsItem fromMap(Map<String, Object> data) {
        DomainListResponseDomainsItem result = new DomainListResponseDomainsItem();
        result.domain = Json.asString(data.get("domain"));
        result.verificationRecord = Json.asObject(data.get("verification-record"), DomainListResponseDomainsItemVerificationRecord::fromMap);
        result.dkim = Json.asObject(data.get("dkim"), DomainListResponseDomainsItemDkim::fromMap);
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "domain", domain);
        Json.put(result, "verification-record", verificationRecord == null ? null : verificationRecord.toMap());
        Json.put(result, "dkim", dkim == null ? null : dkim.toMap());
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
