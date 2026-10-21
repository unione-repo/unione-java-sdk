package io.unione.common.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** Contains HTML/plaintext/AMP parts of the email. Either html or plaintext part is required. */
public class BodyObject {
    /** HTML part of the email body */
    private String html;

    /** Plaintext part of the email body. */
    private String plaintext;

    /**
     * Optional [AMP](http://amp.dev/documentation/guides-and-tutorials/learn/email_fundamentals/) part of
     * the email body.
     */
    private String amp;

    public String getHtml() {
        return html;
    }

    public BodyObject setHtml(String html) {
        this.html = html;
        return this;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public BodyObject setPlaintext(String plaintext) {
        this.plaintext = plaintext;
        return this;
    }

    public String getAmp() {
        return amp;
    }

    public BodyObject setAmp(String amp) {
        this.amp = amp;
        return this;
    }

    /**
     * An HTML body.
     *
     * @param html the HTML part
     * @return the body
     */
    public static BodyObject html(String html) {
        return new BodyObject().setHtml(html);
    }

    /**
     * An HTML body with a plain-text part.
     *
     * @param html the HTML part
     * @param plaintext the plain-text part
     * @return the body
     */
    public static BodyObject html(String html, String plaintext) {
        return new BodyObject().setHtml(html).setPlaintext(plaintext);
    }

    /**
     * A plain-text body.
     *
     * @param plaintext the text
     * @return the body
     */
    public static BodyObject text(String plaintext) {
        return new BodyObject().setPlaintext(plaintext);
    }

    /** Builds the object from decoded JSON. */
    public static BodyObject fromMap(Map<String, Object> data) {
        BodyObject result = new BodyObject();
        result.html = Json.asString(data.get("html"));
        result.plaintext = Json.asString(data.get("plaintext"));
        result.amp = Json.asString(data.get("amp"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "html", html);
        Json.put(result, "plaintext", plaintext);
        Json.put(result, "amp", amp);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
