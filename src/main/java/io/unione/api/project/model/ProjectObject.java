package io.unione.api.project.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** Object with project properties. */
public class ProjectObject {
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

    public String getName() {
        return name;
    }

    public ProjectObject setName(String name) {
        this.name = name;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public ProjectObject setCountry(String country) {
        this.country = country;
        return this;
    }

    public Boolean getSendEnabled() {
        return sendEnabled;
    }

    public ProjectObject setSendEnabled(Boolean sendEnabled) {
        this.sendEnabled = sendEnabled;
        return this;
    }

    public Boolean getCustomUnsubscribeUrlEnabled() {
        return customUnsubscribeUrlEnabled;
    }

    public ProjectObject setCustomUnsubscribeUrlEnabled(Boolean customUnsubscribeUrlEnabled) {
        this.customUnsubscribeUrlEnabled = customUnsubscribeUrlEnabled;
        return this;
    }

    public Integer getBackendDomainId() {
        return backendDomainId;
    }

    public ProjectObject setBackendDomainId(Integer backendDomainId) {
        this.backendDomainId = backendDomainId;
        return this;
    }

    public Integer getUnsubscribePageId() {
        return unsubscribePageId;
    }

    public ProjectObject setUnsubscribePageId(Integer unsubscribePageId) {
        this.unsubscribePageId = unsubscribePageId;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static ProjectObject fromMap(Map<String, Object> data) {
        ProjectObject result = new ProjectObject();
        result.name = Json.asString(data.get("name"));
        result.country = Json.asString(data.get("country"));
        result.sendEnabled = Json.asBoolean(data.get("send_enabled"));
        result.customUnsubscribeUrlEnabled = Json.asBoolean(data.get("custom_unsubscribe_url_enabled"));
        result.backendDomainId = Json.asInteger(data.get("backend_domain_id"));
        result.unsubscribePageId = Json.asInteger(data.get("unsubscribe_page_id"));
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
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
