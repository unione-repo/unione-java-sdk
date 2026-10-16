package io.unione.api.domain.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** DomainGetDnsRecordsResponse */
public class DomainGetDnsRecordsResponse {
    /**
     * "success" string
     * Allowed values: `success`.
     */
    private String status;

    /** Domain to get DNS records for. */
    private String domain;

    /** Record to be added "as is" to verify ownership of this domain. */
    private String verificationRecord;

    /**
     * DKIM signature for the domain. This property contains only the key part, you should prepend it with
     * "v=DKIM1; k=rsa; p=" for the TXT record to be valid.
     */
    private String dkim;

    /** Target of the CNAME record for the "_dmarc" host of the domain. */
    private String dmarc;

    public String getStatus() {
        return status;
    }

    public DomainGetDnsRecordsResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public DomainGetDnsRecordsResponse setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public String getVerificationRecord() {
        return verificationRecord;
    }

    public DomainGetDnsRecordsResponse setVerificationRecord(String verificationRecord) {
        this.verificationRecord = verificationRecord;
        return this;
    }

    public String getDkim() {
        return dkim;
    }

    public DomainGetDnsRecordsResponse setDkim(String dkim) {
        this.dkim = dkim;
        return this;
    }

    public String getDmarc() {
        return dmarc;
    }

    public DomainGetDnsRecordsResponse setDmarc(String dmarc) {
        this.dmarc = dmarc;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static DomainGetDnsRecordsResponse fromMap(Map<String, Object> data) {
        DomainGetDnsRecordsResponse result = new DomainGetDnsRecordsResponse();
        result.status = Json.asString(data.get("status"));
        result.domain = Json.asString(data.get("domain"));
        result.verificationRecord = Json.asString(data.get("verification-record"));
        result.dkim = Json.asString(data.get("dkim"));
        result.dmarc = Json.asString(data.get("dmarc"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "status", status);
        Json.put(result, "domain", domain);
        Json.put(result, "verification-record", verificationRecord);
        Json.put(result, "dkim", dkim);
        Json.put(result, "dmarc", dmarc);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
