package io.unione.api.project.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** ProjectUpdateResponse */
public class ProjectUpdateResponse {
    /**
     * "success" string
     * Allowed values: `success`.
     */
    private String status;

    /**
     * API key of the project. Returned only if the project to update was identified by project_api_key.
     * Not returned in case of updating by project_id.
     */
    private String projectApiKey;

    public String getStatus() {
        return status;
    }

    public ProjectUpdateResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getProjectApiKey() {
        return projectApiKey;
    }

    public ProjectUpdateResponse setProjectApiKey(String projectApiKey) {
        this.projectApiKey = projectApiKey;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static ProjectUpdateResponse fromMap(Map<String, Object> data) {
        ProjectUpdateResponse result = new ProjectUpdateResponse();
        result.status = Json.asString(data.get("status"));
        result.projectApiKey = Json.asString(data.get("project_api_key"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "status", status);
        Json.put(result, "project_api_key", projectApiKey);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
