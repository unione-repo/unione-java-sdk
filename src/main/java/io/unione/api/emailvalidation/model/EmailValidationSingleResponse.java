package io.unione.api.emailvalidation.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** EmailValidationSingleResponse */
public class EmailValidationSingleResponse {
    /**
     * "success" string
     * Allowed values: `success`.
     */
    private String status;

    /** Email address to be checked. */
    private String email;

    /**
     * Possible statuses:
     * <p>
     * - "valid" - the address is valid,
     * - "invalid" - the address is invalid,
     * - "suspicious" - the address looks suspicious,
     * - "unknown" - could not perform validation, the domain’s mail server has not responded within the time limit.
     */
    private String resultValue;

    /**
     * Possible statuses:
     * <p>
     * - "no_mx_record" - no MX record found for the target domain,
     * - "syntax_error" - the address syntax is invalid,
     * - "possible_typo" - the address is likely to have a typo,
     * - "mailbox_not_found" - the address does not exist,
     * - "global_suppression" - the address has been marked as unreachable due to multiple previous delivery errors,
     * - "disposable" - this is a disposable one-time email address which is usually valid for a few minutes only,
     * - "role" - the address is not likely to belong to an actual person, but rather to a certain business staff role,
     * - "abuse" - the address is known to be a source of a large number of complaints, sometimes issued automatically,
     * - "spamtrap" - this email is a spam trap, it is published openly but never used for actual emails. Sending messages to such addresses has a huge negative impact on reputation score,
     * - "smtp_connection_failed" - the domain’s SMTP server does not respond; the address may contain a typo.
     */
    private String cause;

    /** Validity score, from 0 to 100 (0 – invalid, 100 – valid). */
    private Integer validity;

    /** Local part (everything preceding the &#64; sign). */
    private String localPart;

    /** Domain name part. */
    private String domain;

    /** True if the address’ domain does have an MX record, false if does not. */
    private Boolean mxFound;

    /** Preferred MX record for the domain. */
    private String mxRecord;

    /**
     * For addresses which are likely to have typing errors (cause=possible_typo), a suggested variant with
     * a fixed typo.
     */
    private String didYouMean;

    /** Email check date and time, YYYY-MM-DD hh:mm:ss UTC. */
    private String processedAt;

    public String getStatus() {
        return status;
    }

    public EmailValidationSingleResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmailValidationSingleResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getResultValue() {
        return resultValue;
    }

    public EmailValidationSingleResponse setResultValue(String resultValue) {
        this.resultValue = resultValue;
        return this;
    }

    public String getCause() {
        return cause;
    }

    public EmailValidationSingleResponse setCause(String cause) {
        this.cause = cause;
        return this;
    }

    public Integer getValidity() {
        return validity;
    }

    public EmailValidationSingleResponse setValidity(Integer validity) {
        this.validity = validity;
        return this;
    }

    public String getLocalPart() {
        return localPart;
    }

    public EmailValidationSingleResponse setLocalPart(String localPart) {
        this.localPart = localPart;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public EmailValidationSingleResponse setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public Boolean getMxFound() {
        return mxFound;
    }

    public EmailValidationSingleResponse setMxFound(Boolean mxFound) {
        this.mxFound = mxFound;
        return this;
    }

    public String getMxRecord() {
        return mxRecord;
    }

    public EmailValidationSingleResponse setMxRecord(String mxRecord) {
        this.mxRecord = mxRecord;
        return this;
    }

    public String getDidYouMean() {
        return didYouMean;
    }

    public EmailValidationSingleResponse setDidYouMean(String didYouMean) {
        this.didYouMean = didYouMean;
        return this;
    }

    public String getProcessedAt() {
        return processedAt;
    }

    public EmailValidationSingleResponse setProcessedAt(String processedAt) {
        this.processedAt = processedAt;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static EmailValidationSingleResponse fromMap(Map<String, Object> data) {
        EmailValidationSingleResponse result = new EmailValidationSingleResponse();
        result.status = Json.asString(data.get("status"));
        result.email = Json.asString(data.get("email"));
        result.resultValue = Json.asString(data.get("result"));
        result.cause = Json.asString(data.get("cause"));
        result.validity = Json.asInteger(data.get("validity"));
        result.localPart = Json.asString(data.get("local_part"));
        result.domain = Json.asString(data.get("domain"));
        result.mxFound = Json.asBoolean(data.get("mx_found"));
        result.mxRecord = Json.asString(data.get("mx_record"));
        result.didYouMean = Json.asString(data.get("did_you_mean"));
        result.processedAt = Json.asString(data.get("processed_at"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "status", status);
        Json.put(result, "email", email);
        Json.put(result, "result", resultValue);
        Json.put(result, "cause", cause);
        Json.put(result, "validity", validity);
        Json.put(result, "local_part", localPart);
        Json.put(result, "domain", domain);
        Json.put(result, "mx_found", mxFound);
        Json.put(result, "mx_record", mxRecord);
        Json.put(result, "did_you_mean", didYouMean);
        Json.put(result, "processed_at", processedAt);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
