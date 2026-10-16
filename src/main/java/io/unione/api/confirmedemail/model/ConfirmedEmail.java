package io.unione.api.confirmedemail.model;

import io.unione.client.Json;
import io.unione.common.model.VerificationRecordStatus;
import java.util.LinkedHashMap;
import java.util.Map;

/** ConfirmedEmail */
public class ConfirmedEmail {
    private String email;

    /**
     * Confirmation status of the email address.
     * <p>
     * Raw value, kept also when the SDK does not know it.
     */
    private String status;

    public String getEmail() {
        return email;
    }

    public ConfirmedEmail setEmail(String email) {
        this.email = email;
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
    public ConfirmedEmail setStatus(VerificationRecordStatus status) {
        this.status = VerificationRecordStatus.toValue(status);
        return this;
    }

    /** Raw value of the field as sent by the API, also when the SDK does not know it. */
    public String getStatusValue() {
        return status;
    }

    /** Sets the raw value, e.g. one added to the API after this SDK version. */
    public ConfirmedEmail setStatusValue(String status) {
        this.status = status;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static ConfirmedEmail fromMap(Map<String, Object> data) {
        ConfirmedEmail result = new ConfirmedEmail();
        result.email = Json.asString(data.get("email"));
        result.status = Json.asString(data.get("status"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "email", email);
        Json.put(result, "status", status);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
