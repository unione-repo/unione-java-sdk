package io.unione.api.domain.model;

import io.unione.client.Json;
import io.unione.common.model.VerificationRecordStatus;
import java.util.LinkedHashMap;
import java.util.Map;

/** Object describing verification record value and status. */
public class DomainListResponseDomainsItemVerificationRecord {
    /** Record to be added "as is" to verify ownership of this domain. */
    private String value;

    /**
     * Only domains with "confirmed" verification record are allowed as sender domains.
     * <p>
     * Raw value, kept also when the SDK does not know it.
     */
    private String status;

    public String getValue() {
        return value;
    }

    public DomainListResponseDomainsItemVerificationRecord setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Enum value of the field, UNKNOWN_VALUE for a value unknown to this SDK version (the raw one:
     * getStatusValue()), null when absent.
     */
    public VerificationRecordStatus getStatus() {
        return VerificationRecordStatus.fromValue(status);
    }

    /**
     * Sets the field; IllegalArgumentException for UNKNOWN_VALUE (an unknown value goes to
     * setStatusValue()).
     */
    public DomainListResponseDomainsItemVerificationRecord setStatus(VerificationRecordStatus status) {
        this.status = VerificationRecordStatus.toValue(status);
        return this;
    }

    /** Raw value of the field as sent by the API, also when the SDK does not know it. */
    public String getStatusValue() {
        return status;
    }

    /** Sets the raw value, e.g. one added to the API after this SDK version. */
    public DomainListResponseDomainsItemVerificationRecord setStatusValue(String status) {
        this.status = status;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static DomainListResponseDomainsItemVerificationRecord fromMap(Map<String, Object> data) {
        DomainListResponseDomainsItemVerificationRecord result = new DomainListResponseDomainsItemVerificationRecord();
        result.value = Json.asString(data.get("value"));
        result.status = Json.asString(data.get("status"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "value", value);
        Json.put(result, "status", status);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
