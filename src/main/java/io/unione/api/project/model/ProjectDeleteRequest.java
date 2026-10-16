package io.unione.api.project.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** ProjectDeleteRequest */
public class ProjectDeleteRequest {
    /**
     * Unique project identifier. You should identify project to delete either with project_id or with
     * project_api_key field. We recommend usage of project_id as a more secure option.
     */
    private String projectId;

    /**
     * Optional project API key. You should identify project to delete either with project_id or with
     * project_api_key field. We recommend usage of project_id as a more secure option.
     */
    private String projectApiKey;

    public String getProjectId() {
        return projectId;
    }

    public ProjectDeleteRequest setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getProjectApiKey() {
        return projectApiKey;
    }

    public ProjectDeleteRequest setProjectApiKey(String projectApiKey) {
        this.projectApiKey = projectApiKey;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static ProjectDeleteRequest fromMap(Map<String, Object> data) {
        ProjectDeleteRequest result = new ProjectDeleteRequest();
        result.projectId = Json.asString(data.get("project_id"));
        result.projectApiKey = Json.asString(data.get("project_api_key"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "project_id", projectId);
        Json.put(result, "project_api_key", projectApiKey);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
