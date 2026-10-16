package io.unione.common.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** InlineAttachmentsArrayItem */
public class InlineAttachmentsArrayItem {
    /**
     * Attachment type, see [MIME](https://en.wikipedia.org/wiki/MIME). If unsure, use
     * "application/octet-stream".
     */
    private String type;

    /**
     * Attachment content id. For example, "name"="IMAGECID1" should be referenced in HTML like &lt;img
     * src="cid:IMAGECID1"&gt;.
     */
    private String name;

    /**
     * File contents in [base64](https://en.wikipedia.org/wiki/Base64). Maximum file size 7MB (9786710
     * bytes in base64).
     */
    private String content;

    public String getType() {
        return type;
    }

    public InlineAttachmentsArrayItem setType(String type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public InlineAttachmentsArrayItem setName(String name) {
        this.name = name;
        return this;
    }

    public String getContent() {
        return content;
    }

    public InlineAttachmentsArrayItem setContent(String content) {
        this.content = content;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static InlineAttachmentsArrayItem fromMap(Map<String, Object> data) {
        InlineAttachmentsArrayItem result = new InlineAttachmentsArrayItem();
        result.type = Json.asString(data.get("type"));
        result.name = Json.asString(data.get("name"));
        result.content = Json.asString(data.get("content"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "type", type);
        Json.put(result, "name", name);
        Json.put(result, "content", content);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
