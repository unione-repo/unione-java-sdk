package io.unione.api.template.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** TemplateListResponse */
public class TemplateListResponse {
    /**
     * "success" string
     * Allowed values: `success`.
     */
    private String status;

    /** Array of template objects. */
    private List<TemplateListObject> templates;

    public String getStatus() {
        return status;
    }

    public TemplateListResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public List<TemplateListObject> getTemplates() {
        return templates;
    }

    public TemplateListResponse setTemplates(List<TemplateListObject> templates) {
        this.templates = templates;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static TemplateListResponse fromMap(Map<String, Object> data) {
        TemplateListResponse result = new TemplateListResponse();
        result.status = Json.asString(data.get("status"));
        result.templates = Json.asList(data.get("templates"), v0 -> Json.asObject(v0, TemplateListObject::fromMap));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "status", status);
        Json.put(result, "templates", Json.mapList(templates, v0 -> v0.toMap()));
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
