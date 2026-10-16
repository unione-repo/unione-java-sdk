package io.unione.api.system.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** SystemInfoResponse */
public class SystemInfoResponse {
    /**
     * "success" string
     * Allowed values: `success`.
     */
    private String status;

    /** Unique user identifier. */
    private Integer userId;

    /** Email of the user. */
    private String email;

    /**
     * Unqiue project identifier, ASCII string up to 36 characters long. Present only if the API key used
     * for request is the project API key.
     */
    private String projectId;

    /**
     * Project name, unique for user account. Present only if the API key used for request is the project
     * API key.
     */
    private String projectName;

    /**
     * Object with email counter properties. Present only if the API key used for request is the user API
     * key.
     */
    private ProjectAccountingObject projectAccounting;

    /**
     * Object with accounting period properties. Present only if the API key used for request is the user
     * API key.
     */
    private SystemInfoResponseAccounting accounting;

    public String getStatus() {
        return status;
    }

    public SystemInfoResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public SystemInfoResponse setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SystemInfoResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getProjectId() {
        return projectId;
    }

    public SystemInfoResponse setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getProjectName() {
        return projectName;
    }

    public SystemInfoResponse setProjectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public ProjectAccountingObject getProjectAccounting() {
        return projectAccounting;
    }

    public SystemInfoResponse setProjectAccounting(ProjectAccountingObject projectAccounting) {
        this.projectAccounting = projectAccounting;
        return this;
    }

    public SystemInfoResponseAccounting getAccounting() {
        return accounting;
    }

    public SystemInfoResponse setAccounting(SystemInfoResponseAccounting accounting) {
        this.accounting = accounting;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static SystemInfoResponse fromMap(Map<String, Object> data) {
        SystemInfoResponse result = new SystemInfoResponse();
        result.status = Json.asString(data.get("status"));
        result.userId = Json.asInteger(data.get("user_id"));
        result.email = Json.asString(data.get("email"));
        result.projectId = Json.asString(data.get("project_id"));
        result.projectName = Json.asString(data.get("project_name"));
        result.projectAccounting = Json.asObject(data.get("project_accounting"), ProjectAccountingObject::fromMap);
        result.accounting = Json.asObject(data.get("accounting"), SystemInfoResponseAccounting::fromMap);
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "status", status);
        Json.put(result, "user_id", userId);
        Json.put(result, "email", email);
        Json.put(result, "project_id", projectId);
        Json.put(result, "project_name", projectName);
        Json.put(result, "project_accounting", projectAccounting == null ? null : projectAccounting.toMap());
        Json.put(result, "accounting", accounting == null ? null : accounting.toMap());
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
