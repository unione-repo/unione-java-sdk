package io.unione.api.project.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** ProjectUpdateRequest */
public class ProjectUpdateRequest {
    /**
     * Unique project identifier. You should identify project to update either with project_id or with
     * project_api_key field. But we recommend usage of project_id as a more secure option.
     */
    private String projectId;

    /**
     * Optional project API key. You should identify project to update either with project_id or with
     * project_api_key field. But we recommend usage of project_id as a more secure option.
     */
    private String projectApiKey;

    /** Object with project properties. */
    private ProjectUpdateObject project;

    public String getProjectId() {
        return projectId;
    }

    public ProjectUpdateRequest setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getProjectApiKey() {
        return projectApiKey;
    }

    public ProjectUpdateRequest setProjectApiKey(String projectApiKey) {
        this.projectApiKey = projectApiKey;
        return this;
    }

    public ProjectUpdateObject getProject() {
        return project;
    }

    public ProjectUpdateRequest setProject(ProjectUpdateObject project) {
        this.project = project;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static ProjectUpdateRequest fromMap(Map<String, Object> data) {
        ProjectUpdateRequest result = new ProjectUpdateRequest();
        result.projectId = Json.asString(data.get("project_id"));
        result.projectApiKey = Json.asString(data.get("project_api_key"));
        result.project = Json.asObject(data.get("project"), ProjectUpdateObject::fromMap);
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "project_id", projectId);
        Json.put(result, "project_api_key", projectApiKey);
        Json.put(result, "project", project == null ? null : project.toMap());
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
