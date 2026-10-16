package io.unione.api.project.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** ProjectCreateResponse */
public class ProjectCreateResponse {
    /**
     * "success" string
     * Allowed values: `success`.
     */
    private String status;

    /** Unqiue project identifier, ASCII string up to 36 characters long. */
    private String projectId;

    /**
     * API key of the project. You can use it instead of the user API key parameter in all methods except
     * project/* methods.
     */
    private String projectApiKey;

    public String getStatus() {
        return status;
    }

    public ProjectCreateResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getProjectId() {
        return projectId;
    }

    public ProjectCreateResponse setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getProjectApiKey() {
        return projectApiKey;
    }

    public ProjectCreateResponse setProjectApiKey(String projectApiKey) {
        this.projectApiKey = projectApiKey;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static ProjectCreateResponse fromMap(Map<String, Object> data) {
        ProjectCreateResponse result = new ProjectCreateResponse();
        result.status = Json.asString(data.get("status"));
        result.projectId = Json.asString(data.get("project_id"));
        result.projectApiKey = Json.asString(data.get("project_api_key"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "status", status);
        Json.put(result, "project_id", projectId);
        Json.put(result, "project_api_key", projectApiKey);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
