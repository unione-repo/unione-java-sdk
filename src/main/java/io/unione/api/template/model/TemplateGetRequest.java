package io.unione.api.template.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** TemplateGetRequest */
public class TemplateGetRequest {
    /** Template id. */
    private String id;

    public String getId() {
        return id;
    }

    public TemplateGetRequest setId(String id) {
        this.id = id;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static TemplateGetRequest fromMap(Map<String, Object> data) {
        TemplateGetRequest result = new TemplateGetRequest();
        result.id = Json.asString(data.get("id"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "id", id);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
