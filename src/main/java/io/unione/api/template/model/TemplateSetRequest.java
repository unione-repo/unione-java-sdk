package io.unione.api.template.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** Object with all the properties of the template. */
public class TemplateSetRequest {
    /** Object with all the template properties. */
    private TemplateObject template;

    public TemplateObject getTemplate() {
        return template;
    }

    public TemplateSetRequest setTemplate(TemplateObject template) {
        this.template = template;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static TemplateSetRequest fromMap(Map<String, Object> data) {
        TemplateSetRequest result = new TemplateSetRequest();
        result.template = Json.asObject(data.get("template"), TemplateObject::fromMap);
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "template", template == null ? null : template.toMap());
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
