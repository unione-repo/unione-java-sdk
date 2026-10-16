package io.unione.api.project.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** ProjectListResponse */
public class ProjectListResponse {
    /**
     * "success" string
     * Allowed values: `success`.
     */
    private String status;

    /** Array of objects where each object represents one project. */
    private List<ProjectListObject> projects;

    public String getStatus() {
        return status;
    }

    public ProjectListResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public List<ProjectListObject> getProjects() {
        return projects;
    }

    public ProjectListResponse setProjects(List<ProjectListObject> projects) {
        this.projects = projects;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static ProjectListResponse fromMap(Map<String, Object> data) {
        ProjectListResponse result = new ProjectListResponse();
        result.status = Json.asString(data.get("status"));
        result.projects = Json.asList(data.get("projects"), v0 -> Json.asObject(v0, ProjectListObject::fromMap));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "status", status);
        Json.put(result, "projects", Json.mapList(projects, v0 -> v0.toMap()));
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
