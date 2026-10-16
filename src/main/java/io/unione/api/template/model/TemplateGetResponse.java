package io.unione.api.template.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** TemplateGetResponse */
public class TemplateGetResponse {
    /**
     * "success" string
     * Allowed values: `success`.
     */
    private String status;

    /** Object with all the template properties. */
    private TemplateObject template;

    public String getStatus() {
        return status;
    }

    public TemplateGetResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public TemplateObject getTemplate() {
        return template;
    }

    public TemplateGetResponse setTemplate(TemplateObject template) {
        this.template = template;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static TemplateGetResponse fromMap(Map<String, Object> data) {
        TemplateGetResponse result = new TemplateGetResponse();
        result.status = Json.asString(data.get("status"));
        result.template = Json.asObject(data.get("template"), TemplateObject::fromMap);
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "status", status);
        Json.put(result, "template", template == null ? null : template.toMap());
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
