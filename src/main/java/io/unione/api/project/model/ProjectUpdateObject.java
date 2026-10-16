package io.unione.api.project.model;

import io.unione.client.Json;
import io.unione.common.model.EmailCounterMode;
import java.util.LinkedHashMap;
import java.util.Map;

/** Object with project properties. */
public class ProjectUpdateObject {
    /** Project name, unique for user account. */
    private String name;

    /**
     * ISO-3166 alpha-2 country code. If set, UniOne treats project personal data according to country
     * laws, e.g. GDPR for european countries.
     */
    private String country;

    /**
     * Whether email sending is enabled for this project or not.
     * Default: `True`.
     */
    private Boolean sendEnabled;

    /**
     * If it's false then UniOne adds default unsubscribe footer to each email sent with API key of the
     * project. If it's true then appending default unsubscribe footer for this project is avoided and
     * sending with custom unsubscribe url or even without unsubscribe url is permitted for the project.
     * Please note that custom_unsubscribe_url_enabled=true is available only if removing unsubscribe link
     * is approved for the user account by support. More on this
     * [here](https://docs.unione.io/en/unsubscribe-link). When custom_unsubscribe_url_enabled is skipped
     * on creating a project, it's value is taken from user
     */
    private Boolean customUnsubscribeUrlEnabled;

    /**
     * A unique domain identifier which will determine the [tracking
     * domain](https://docs.unione.io/en/tracking-domains) or [dedicated IP](/dedicated-ip) pool to be used
     * by default. If the value is not specified, a default backend domain will be assigned for your
     * account or project instead.
     */
    private Integer backendDomainId;

    /**
     * A unique identifier that defines the default unsubscribe page. If absent, the account/project will
     * be assigned the system default unsubscribe page.
     */
    private Integer unsubscribePageId;

    /**
     * Project's email counter value. 0 value only is accepted (which means resetting the counter to zero),
     * or refreshes automatically depending on the counter mode set with the 'email_counter_mode'
     * parameter. If the request doesn't contain the field or contains null, the limit value doesn't
     * change.
     */
    private Long emailCounter;

    /**
     * Allows you to limit the number of emails sent by the project. The sending stops when the limit is
     * reached. Any non-negative 64-bit integer is acceptable. Setting to 0 means no limit. If the request
     * doesn't contain the field or contains null, the limit value doesn't change.
     * Default: `0`.
     */
    private Long emailCounterLimit;

    /**
     * Sets the email counter mode. It allows you to choose whether the counter should reset automatically,
     * or remain permanent. The following values are acceptable: 'default' - the counter resets to zero
     * automatically at the beginning of each accounting period, 'permanent' - the counter never resets
     * automatically. The reset can be done only manually by the user in the request with the passes 0
     * value in the email_counter field. If the request doesn't contain the field or contains a null value,
     * the limit value doesn't change.
     * Default: `default`.
     * <p>
     * Raw value, kept also when the SDK does not know it.
     */
    private String emailCounterMode;

    public String getName() {
        return name;
    }

    public ProjectUpdateObject setName(String name) {
        this.name = name;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public ProjectUpdateObject setCountry(String country) {
        this.country = country;
        return this;
    }

    public Boolean getSendEnabled() {
        return sendEnabled;
    }

    public ProjectUpdateObject setSendEnabled(Boolean sendEnabled) {
        this.sendEnabled = sendEnabled;
        return this;
    }

    public Boolean getCustomUnsubscribeUrlEnabled() {
        return customUnsubscribeUrlEnabled;
    }

    public ProjectUpdateObject setCustomUnsubscribeUrlEnabled(Boolean customUnsubscribeUrlEnabled) {
        this.customUnsubscribeUrlEnabled = customUnsubscribeUrlEnabled;
        return this;
    }

    public Integer getBackendDomainId() {
        return backendDomainId;
    }

    public ProjectUpdateObject setBackendDomainId(Integer backendDomainId) {
        this.backendDomainId = backendDomainId;
        return this;
    }

    public Integer getUnsubscribePageId() {
        return unsubscribePageId;
    }

    public ProjectUpdateObject setUnsubscribePageId(Integer unsubscribePageId) {
        this.unsubscribePageId = unsubscribePageId;
        return this;
    }

    public Long getEmailCounter() {
        return emailCounter;
    }

    public ProjectUpdateObject setEmailCounter(Long emailCounter) {
        this.emailCounter = emailCounter;
        return this;
    }

    public Long getEmailCounterLimit() {
        return emailCounterLimit;
    }

    public ProjectUpdateObject setEmailCounterLimit(Long emailCounterLimit) {
        this.emailCounterLimit = emailCounterLimit;
        return this;
    }

    /**
     * Enum value of the field, UNKNOWN_VALUE for a value unknown to this SDK version (the raw one:
     * getEmailCounterModeValue()), null when absent.
     */
    public EmailCounterMode getEmailCounterMode() {
        return EmailCounterMode.fromValue(emailCounterMode);
    }

    /**
     * Sets the field; IllegalArgumentException for UNKNOWN_VALUE (an unknown value goes to
     * setEmailCounterModeValue()).
     */
    public ProjectUpdateObject setEmailCounterMode(EmailCounterMode emailCounterMode) {
        this.emailCounterMode = EmailCounterMode.toValue(emailCounterMode);
        return this;
    }

    /** Raw value of the field as sent by the API, also when the SDK does not know it. */
    public String getEmailCounterModeValue() {
        return emailCounterMode;
    }

    /** Sets the raw value, e.g. one added to the API after this SDK version. */
    public ProjectUpdateObject setEmailCounterModeValue(String emailCounterMode) {
        this.emailCounterMode = emailCounterMode;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static ProjectUpdateObject fromMap(Map<String, Object> data) {
        ProjectUpdateObject result = new ProjectUpdateObject();
        result.name = Json.asString(data.get("name"));
        result.country = Json.asString(data.get("country"));
        result.sendEnabled = Json.asBoolean(data.get("send_enabled"));
        result.customUnsubscribeUrlEnabled = Json.asBoolean(data.get("custom_unsubscribe_url_enabled"));
        result.backendDomainId = Json.asInteger(data.get("backend_domain_id"));
        result.unsubscribePageId = Json.asInteger(data.get("unsubscribe_page_id"));
        result.emailCounter = Json.asLong(data.get("email_counter"));
        result.emailCounterLimit = Json.asLong(data.get("email_counter_limit"));
        result.emailCounterMode = Json.asString(data.get("email_counter_mode"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "name", name);
        Json.put(result, "country", country);
        Json.put(result, "send_enabled", sendEnabled);
        Json.put(result, "custom_unsubscribe_url_enabled", customUnsubscribeUrlEnabled);
        Json.put(result, "backend_domain_id", backendDomainId);
        Json.put(result, "unsubscribe_page_id", unsubscribePageId);
        Json.put(result, "email_counter", emailCounter);
        Json.put(result, "email_counter_limit", emailCounterLimit);
        Json.put(result, "email_counter_mode", emailCounterMode);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
