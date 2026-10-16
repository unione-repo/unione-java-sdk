package io.unione.api.template.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** TemplateDeleteRequest */
public class TemplateDeleteRequest {
    /** Template id. */
    private String id;

    public String getId() {
        return id;
    }

    public TemplateDeleteRequest setId(String id) {
        this.id = id;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static TemplateDeleteRequest fromMap(Map<String, Object> data) {
        TemplateDeleteRequest result = new TemplateDeleteRequest();
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
