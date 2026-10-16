package io.unione.common.model;

import io.unione.client.Json;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Contains email headers, maximum 50. Only headers with “X-” name prefix are accepted, all other are
 * ignored, for example X-UNIONE-Global-Language, X-UNIONE-Template-Engine. Standard headers "To,"
 * "CC," and "BCC" are passed without the "X-." Yet, they are processed in a particular way and, as a
 * result, have a number of restrictions. You can find more details about it [here](/cc-and-bcc). If
 * our support have approved omitting standard unsubscription block for you, you can also pass
 * List-Unsubscribe, List-Subscribe, List-Help, List-Owner, List-Archive, In-Reply-To and References
 * headers.
 * <p>
 * Keys beyond the named fields (any header, e.g. X-MyHeader, CC, List-Unsubscribe) go to the extra
 * map: putExtra("X-MyHeader", "value"). In JSON they sit next to the named fields; a named field wins
 * over an extra key with the same name.
 */
public class HeadersObject {
    /** JSON keys of the named fields. */
    private static final Set<String> NAMED = Collections.unmodifiableSet(new HashSet<>(
        Arrays.asList("X-UNIONE-Global-Language", "X-UNIONE-Template-Engine")));

    /**
     * The language of the unsubscribe footer and unsubscribe page. Languages supported are: "be", "de",
     * "en", "es", "fr", "it", "pl", "pt", "ru", "ua", "kz".
     */
    private String xUNIONEGlobalLanguage;

    /**
     * The template engine for handling the substitutions(merge tags), “simple”, “velocity”, “liquid” or
     * “none”. Default value is “simple”. Has a priority over the value allowed in the “template_engine”
     * parameter.
     */
    private String xUNIONETemplateEngine;

    /** Keys beyond the named fields (additionalProperties). */
    private Map<String, String> extra;

    public String getXUNIONEGlobalLanguage() {
        return xUNIONEGlobalLanguage;
    }

    public HeadersObject setXUNIONEGlobalLanguage(String xUNIONEGlobalLanguage) {
        this.xUNIONEGlobalLanguage = xUNIONEGlobalLanguage;
        return this;
    }

    public String getXUNIONETemplateEngine() {
        return xUNIONETemplateEngine;
    }

    public HeadersObject setXUNIONETemplateEngine(String xUNIONETemplateEngine) {
        this.xUNIONETemplateEngine = xUNIONETemplateEngine;
        return this;
    }

    /** Keys beyond the named fields, null when there are none. */
    public Map<String, String> getExtra() {
        return extra;
    }

    /** Replaces the keys beyond the named fields. */
    public HeadersObject setExtra(Map<String, String> extra) {
        this.extra = extra;
        return this;
    }

    /** Adds a key beyond the named fields, e.g. putExtra("X-MyHeader", "value"). */
    public HeadersObject putExtra(String key, String value) {
        if (extra == null) {
            extra = new LinkedHashMap<>();
        }
        extra.put(key, value);
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static HeadersObject fromMap(Map<String, Object> data) {
        HeadersObject result = new HeadersObject();
        result.xUNIONEGlobalLanguage = Json.asString(data.get("X-UNIONE-Global-Language"));
        result.xUNIONETemplateEngine = Json.asString(data.get("X-UNIONE-Template-Engine"));
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (!NAMED.contains(entry.getKey())) {
                result.putExtra(entry.getKey(), Json.asString(entry.getValue()));
            }
        }
        return result;
    }

    /**
     * JSON-ready map, null fields are omitted. Extra keys follow the named fields, a named field wins on a
     * clash.
     */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "X-UNIONE-Global-Language", xUNIONEGlobalLanguage);
        Json.put(result, "X-UNIONE-Template-Engine", xUNIONETemplateEngine);
        if (extra != null) {
            for (Map.Entry<String, String> entry : extra.entrySet()) {
                if (entry.getValue() != null && !result.containsKey(entry.getKey())) {
                    result.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
