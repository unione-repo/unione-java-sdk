package io.unione.api.project.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** ProjectCreateRequest */
public class ProjectCreateRequest {
    /** Object with project properties. */
    private ProjectObject project;

    public ProjectObject getProject() {
        return project;
    }

    public ProjectCreateRequest setProject(ProjectObject project) {
        this.project = project;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static ProjectCreateRequest fromMap(Map<String, Object> data) {
        ProjectCreateRequest result = new ProjectCreateRequest();
        result.project = Json.asObject(data.get("project"), ProjectObject::fromMap);
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "project", project == null ? null : project.toMap());
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
