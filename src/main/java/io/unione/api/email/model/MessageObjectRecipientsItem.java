package io.unione.api.email.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** MessageObjectRecipientsItem */
public class MessageObjectRecipientsItem {
    /** Recipient email */
    private String email;

    /**
     * Object to pass the substitutions(merge tags) for the recipient (e.g. recipient name, discount code,
     * password change link, etc. See [Template engines](https://docs.unione.io/en/template-engines)). The
     * substitutions can be used in the following parameters:
     * <p>
     * - body.html
     * - body.plaintext
     * - body.amp
     * - subject
     * - from_name
     * - headers["List-Unsubscribe"]
     * - options.unsubscribe_url
     * A substitution name can consist from latin characters, numbers and "_" symbol, and should start with
     * the letter. There's a special substitution "to_name" which is used to put recipent's name like "Name
     * Surname" to include it to SMTP header "To" in the form "Name Surname &lt;email&#64;example.com&gt;". The
     * "to_name" length is limited to 78 symbols.
     */
    private Map<String, String> substitutions;

    /**
     * Object for passing the metadata, such as "key": "value". Max key quantity: 10. Max key length: 64
     * symbols. Max value length: 1024 symbols. The metadata is returned by
     * [webhook](https://docs.unione.io/en/web-api-ref#webhook) and
     * [event-dump](https://docs.unione.io/en/web-api-ref#event-dump) methods. If you pass strign up to 128
     * bit with "campaign_id" field name (name is configurable thru support), it will be considered as
     * campaign identifier in statistics.
     */
    private Map<String, String> metadata;

    public String getEmail() {
        return email;
    }

    public MessageObjectRecipientsItem setEmail(String email) {
        this.email = email;
        return this;
    }

    public Map<String, String> getSubstitutions() {
        return substitutions;
    }

    public MessageObjectRecipientsItem setSubstitutions(Map<String, String> substitutions) {
        this.substitutions = substitutions;
        return this;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public MessageObjectRecipientsItem setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static MessageObjectRecipientsItem fromMap(Map<String, Object> data) {
        MessageObjectRecipientsItem result = new MessageObjectRecipientsItem();
        result.email = Json.asString(data.get("email"));
        result.substitutions = Json.asMapOf(data.get("substitutions"), Json::asString);
        result.metadata = Json.asMapOf(data.get("metadata"), Json::asString);
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "email", email);
        Json.put(result, "substitutions", substitutions);
        Json.put(result, "metadata", metadata);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
