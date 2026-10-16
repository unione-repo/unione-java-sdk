package io.unione.api.project.model;

import io.unione.client.Json;
import io.unione.common.model.EmailCounterMode;
import java.util.LinkedHashMap;
import java.util.Map;

/** ProjectListObject */
public class ProjectListObject {
    /** Unqiue project identifier, ASCII string up to 36 characters long. */
    private String id;

    /**
     * API key of the project. You can use it instead of the user API key parameter in all methods except
     * project/* methods.
     */
    private String apiKey;

    /** Project name, unique for user account. */
    private String name;

    /**
     * ISO-3166 alpha-2 country code. If set, UniOne treats project personal data according to country
     * laws, e.g. GDPR for european countries.
     */
    private String country;

    /** Date and time of project creation in UTC timezone in "YYYY-MM-DD hh:mm:ss" format. */
    private String regTime;

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

    /** Project's email counter value. It increases on every email sent. */
    private Long emailCounter;

    /**
     * Allows you to limit the number of emails sent by the project. The sending stops when the limit is
     * reached. Any non-negative 64-bit integer is acceptable. 0 means no limit.
     */
    private Long emailCounterLimit;

    /**
     * 'default' - the counter resets to zero automatically at the beginning of each accounting period.
     * 'permanent' - the counter never resets automatically.
     * Default: `default`.
     * <p>
     * Raw value, kept also when the SDK does not know it.
     */
    private String emailCounterMode;

    /**
     * A unique identifier that defines the default unsubscribe page. If absent, the account/project will
     * be assigned the system default unsubscribe page.
     */
    private Integer unsubscribePageId;

    public String getId() {
        return id;
    }

    public ProjectListObject setId(String id) {
        this.id = id;
        return this;
    }

    public String getApiKey() {
        return apiKey;
    }

    public ProjectListObject setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProjectListObject setName(String name) {
        this.name = name;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public ProjectListObject setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getRegTime() {
        return regTime;
    }

    public ProjectListObject setRegTime(String regTime) {
        this.regTime = regTime;
        return this;
    }

    public Boolean getSendEnabled() {
        return sendEnabled;
    }

    public ProjectListObject setSendEnabled(Boolean sendEnabled) {
        this.sendEnabled = sendEnabled;
        return this;
    }

    public Boolean getCustomUnsubscribeUrlEnabled() {
        return customUnsubscribeUrlEnabled;
    }

    public ProjectListObject setCustomUnsubscribeUrlEnabled(Boolean customUnsubscribeUrlEnabled) {
        this.customUnsubscribeUrlEnabled = customUnsubscribeUrlEnabled;
        return this;
    }

    public Integer getBackendDomainId() {
        return backendDomainId;
    }

    public ProjectListObject setBackendDomainId(Integer backendDomainId) {
        this.backendDomainId = backendDomainId;
        return this;
    }

    public Long getEmailCounter() {
        return emailCounter;
    }

    public ProjectListObject setEmailCounter(Long emailCounter) {
        this.emailCounter = emailCounter;
        return this;
    }

    public Long getEmailCounterLimit() {
        return emailCounterLimit;
    }

    public ProjectListObject setEmailCounterLimit(Long emailCounterLimit) {
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
    public ProjectListObject setEmailCounterMode(EmailCounterMode emailCounterMode) {
        this.emailCounterMode = EmailCounterMode.toValue(emailCounterMode);
        return this;
    }

    /** Raw value of the field as sent by the API, also when the SDK does not know it. */
    public String getEmailCounterModeValue() {
        return emailCounterMode;
    }

    /** Sets the raw value, e.g. one added to the API after this SDK version. */
    public ProjectListObject setEmailCounterModeValue(String emailCounterMode) {
        this.emailCounterMode = emailCounterMode;
        return this;
    }

    public Integer getUnsubscribePageId() {
        return unsubscribePageId;
    }

    public ProjectListObject setUnsubscribePageId(Integer unsubscribePageId) {
        this.unsubscribePageId = unsubscribePageId;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static ProjectListObject fromMap(Map<String, Object> data) {
        ProjectListObject result = new ProjectListObject();
        result.id = Json.asString(data.get("id"));
        result.apiKey = Json.asString(data.get("api_key"));
        result.name = Json.asString(data.get("name"));
        result.country = Json.asString(data.get("country"));
        result.regTime = Json.asString(data.get("reg_time"));
        result.sendEnabled = Json.asBoolean(data.get("send_enabled"));
        result.customUnsubscribeUrlEnabled = Json.asBoolean(data.get("custom_unsubscribe_url_enabled"));
        result.backendDomainId = Json.asInteger(data.get("backend_domain_id"));
        result.emailCounter = Json.asLong(data.get("email_counter"));
        result.emailCounterLimit = Json.asLong(data.get("email_counter_limit"));
        result.emailCounterMode = Json.asString(data.get("email_counter_mode"));
        result.unsubscribePageId = Json.asInteger(data.get("unsubscribe_page_id"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "id", id);
        Json.put(result, "api_key", apiKey);
        Json.put(result, "name", name);
        Json.put(result, "country", country);
        Json.put(result, "reg_time", regTime);
        Json.put(result, "send_enabled", sendEnabled);
        Json.put(result, "custom_unsubscribe_url_enabled", customUnsubscribeUrlEnabled);
        Json.put(result, "backend_domain_id", backendDomainId);
        Json.put(result, "email_counter", emailCounter);
        Json.put(result, "email_counter_limit", emailCounterLimit);
        Json.put(result, "email_counter_mode", emailCounterMode);
        Json.put(result, "unsubscribe_page_id", unsubscribePageId);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
