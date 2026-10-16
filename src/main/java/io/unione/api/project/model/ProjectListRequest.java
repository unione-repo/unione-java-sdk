package io.unione.api.project.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** ProjectListRequest */
public class ProjectListRequest {
    /** Optional possibility to specify project_id to include only this project to list. */
    private String projectId;

    /** Optional possibility to specify project_api_key to include only this project to list. */
    private String projectApiKey;

    public String getProjectId() {
        return projectId;
    }

    public ProjectListRequest setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getProjectApiKey() {
        return projectApiKey;
    }

    public ProjectListRequest setProjectApiKey(String projectApiKey) {
        this.projectApiKey = projectApiKey;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static ProjectListRequest fromMap(Map<String, Object> data) {
        ProjectListRequest result = new ProjectListRequest();
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
