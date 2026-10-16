package io.unione.api.template.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** TemplateListRequest */
public class TemplateListRequest {
    /**
     * Limits the number of templates returned, default is 50.
     * Default: `50`.
     */
    private Integer limit;

    /**
     * Index of the first template in the list, starting with 0.
     * Default: `0`.
     */
    private Integer offset;

    public Integer getLimit() {
        return limit;
    }

    public TemplateListRequest setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public TemplateListRequest setOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static TemplateListRequest fromMap(Map<String, Object> data) {
        TemplateListRequest result = new TemplateListRequest();
        result.limit = Json.asInteger(data.get("limit"));
        result.offset = Json.asInteger(data.get("offset"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "limit", limit);
        Json.put(result, "offset", offset);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
