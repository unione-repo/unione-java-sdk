package io.unione.api.template.model;

import io.unione.client.Json;
import io.unione.common.model.AttachmentsArrayItem;
import io.unione.common.model.BodyObject;
import io.unione.common.model.HeadersObject;
import io.unione.common.model.InlineAttachmentsArrayItem;
import io.unione.common.model.TemplateEngine;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** TemplateListObject */
public class TemplateListObject {
    /** Template unique identifier. */
    private String id;

    /** Template name. */
    private String name;

    /**
     * Editor type, "html" or "visual". Default is "html". You can create a template with "visual" editor
     * type only in web interface.
     * <p>
     * Raw value, kept also when the SDK does not know it.
     */
    private String editorType;

    /**
     * The [template engine](https://docs.unione.io/en/template-engines) for handling the
     * substitutions(merge tags), "simple", "velocity", "liquid" or "none". Default value is "simple".
     * <p>
     * Raw value, kept also when the SDK does not know it.
     */
    private String templateEngine;

    /**
     * Object for passing the substitutions(merge tags) common for all recipients - e.g., company name. If
     * the substitution names are duplicated in object "substitutions", the values of the variables will be
     * taken from the object "substitutions". The substitutions can be used in the following parameters:
     * <p>
     * - body.html
     * - body.plaintext
     * - body.amp
     * - subject
     * - from_name
     * - options.unsubscribe_url
     * A substitution name can consist from latin characeters, numbers and "_" symbol, and should start
     * with the letter.
     */
    private Map<String, String> globalSubstitutions;

    /**
     * Object for passing the metadata common for all the recipients, such as "key": "value". Max key
     * quantity: 10. Max key length: 64 symbols. Max value length: 1024 symbols. The metadata is returned
     * by [webhook](https://docs.unione.io/en/web-api-ref#webhook). To group single emails into a campaign,
     * you can use the system key 'campaign_id' with a value in the form of a non-negative decimal integer
     * up to 128 bits or a UUID in the format 'c7703772-453e-4c77-a9ae-9a1b94051b5a'. Incorrect values will
     * be treated as '0'.
     */
    private Map<String, String> globalMetadata;

    /** Contains HTML/plaintext/AMP parts of the email. Either html or plaintext part is required. */
    private BodyObject body;

    /** Email subject. */
    private String subject;

    /** Sender's email. */
    private String fromEmail;

    /** Sender's name. */
    private String fromName;

    /** Optional Reply-To email (in case it's different to sender's email) */
    private String replyTo;

    /**
     * Optional Reply-To name (if reply_to email is specified and you want to display not only this email
     * but also the name)
     */
    private String replyToName;

    /**
     * 1 = click tracking is on (default), 0 = click tracking is off. To use track_links = 0, you need to
     * ask support to enable this feature.
     * Allowed values: `0`, `1`.
     */
    private Integer trackLinks;

    /**
     * 1 = read tracking is on (default), 0=read tracking is off. To use track_read = 0, you need to ask
     * support to enable this feature.
     * Allowed values: `0`, `1`.
     */
    private Integer trackRead;

    /**
     * Contains email headers, maximum 50. Only headers with “X-” name prefix are accepted, all other are
     * ignored, for example X-UNIONE-Global-Language, X-UNIONE-Template-Engine. Standard headers "To,"
     * "CC," and "BCC" are passed without the "X-." Yet, they are processed in a particular way and, as a
     * result, have a number of restrictions. You can find more details about it [here](/cc-and-bcc). If
     * our support have approved omitting standard unsubscription block for you, you can also pass
     * List-Unsubscribe, List-Subscribe, List-Help, List-Owner, List-Archive, In-Reply-To and References
     * headers.
     */
    private HeadersObject headers;

    /** Optional array of attachments */
    private List<AttachmentsArrayItem> attachments;

    /**
     * Optional array of inline attachments, e.g. for including images in email instead of loading them
     * from external URL.
     */
    private List<InlineAttachmentsArrayItem> inlineAttachments;

    /** Template creation date and time in UTC timezone in format "YYYY-MM-DD hh:mm:ss" */
    private String created;

    /** Unique user identifier. */
    private Integer userId;

    /** Unqiue project identifier, ASCII string up to 36 characters long. */
    private String projectId;

    /** Project name, unique for user account. */
    private String projectName;

    public String getId() {
        return id;
    }

    public TemplateListObject setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TemplateListObject setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Enum value of the field, UNKNOWN_VALUE for a value unknown to this SDK version (the raw one:
     * getEditorTypeValue()), null when absent.
     */
    public EditorType getEditorType() {
        return EditorType.fromValue(editorType);
    }

    /**
     * Sets the field; IllegalArgumentException for UNKNOWN_VALUE (an unknown value goes to
     * setEditorTypeValue()).
     */
    public TemplateListObject setEditorType(EditorType editorType) {
        this.editorType = EditorType.toValue(editorType);
        return this;
    }

    /** Raw value of the field as sent by the API, also when the SDK does not know it. */
    public String getEditorTypeValue() {
        return editorType;
    }

    /** Sets the raw value, e.g. one added to the API after this SDK version. */
    public TemplateListObject setEditorTypeValue(String editorType) {
        this.editorType = editorType;
        return this;
    }

    /**
     * Enum value of the field, UNKNOWN_VALUE for a value unknown to this SDK version (the raw one:
     * getTemplateEngineValue()), null when absent.
     */
    public TemplateEngine getTemplateEngine() {
        return TemplateEngine.fromValue(templateEngine);
    }

    /**
     * Sets the field; IllegalArgumentException for UNKNOWN_VALUE (an unknown value goes to
     * setTemplateEngineValue()).
     */
    public TemplateListObject setTemplateEngine(TemplateEngine templateEngine) {
        this.templateEngine = TemplateEngine.toValue(templateEngine);
        return this;
    }

    /** Raw value of the field as sent by the API, also when the SDK does not know it. */
    public String getTemplateEngineValue() {
        return templateEngine;
    }

    /** Sets the raw value, e.g. one added to the API after this SDK version. */
    public TemplateListObject setTemplateEngineValue(String templateEngine) {
        this.templateEngine = templateEngine;
        return this;
    }

    public Map<String, String> getGlobalSubstitutions() {
        return globalSubstitutions;
    }

    public TemplateListObject setGlobalSubstitutions(Map<String, String> globalSubstitutions) {
        this.globalSubstitutions = globalSubstitutions;
        return this;
    }

    public Map<String, String> getGlobalMetadata() {
        return globalMetadata;
    }

    public TemplateListObject setGlobalMetadata(Map<String, String> globalMetadata) {
        this.globalMetadata = globalMetadata;
        return this;
    }

    public BodyObject getBody() {
        return body;
    }

    public TemplateListObject setBody(BodyObject body) {
        this.body = body;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public TemplateListObject setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public TemplateListObject setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
        return this;
    }

    public String getFromName() {
        return fromName;
    }

    public TemplateListObject setFromName(String fromName) {
        this.fromName = fromName;
        return this;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public TemplateListObject setReplyTo(String replyTo) {
        this.replyTo = replyTo;
        return this;
    }

    public String getReplyToName() {
        return replyToName;
    }

    public TemplateListObject setReplyToName(String replyToName) {
        this.replyToName = replyToName;
        return this;
    }

    public Integer getTrackLinks() {
        return trackLinks;
    }

    public TemplateListObject setTrackLinks(Integer trackLinks) {
        this.trackLinks = trackLinks;
        return this;
    }

    public Integer getTrackRead() {
        return trackRead;
    }

    public TemplateListObject setTrackRead(Integer trackRead) {
        this.trackRead = trackRead;
        return this;
    }

    public HeadersObject getHeaders() {
        return headers;
    }

    public TemplateListObject setHeaders(HeadersObject headers) {
        this.headers = headers;
        return this;
    }

    public List<AttachmentsArrayItem> getAttachments() {
        return attachments;
    }

    public TemplateListObject setAttachments(List<AttachmentsArrayItem> attachments) {
        this.attachments = attachments;
        return this;
    }

    public List<InlineAttachmentsArrayItem> getInlineAttachments() {
        return inlineAttachments;
    }

    public TemplateListObject setInlineAttachments(List<InlineAttachmentsArrayItem> inlineAttachments) {
        this.inlineAttachments = inlineAttachments;
        return this;
    }

    public String getCreated() {
        return created;
    }

    public TemplateListObject setCreated(String created) {
        this.created = created;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public TemplateListObject setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getProjectId() {
        return projectId;
    }

    public TemplateListObject setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getProjectName() {
        return projectName;
    }

    public TemplateListObject setProjectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static TemplateListObject fromMap(Map<String, Object> data) {
        TemplateListObject result = new TemplateListObject();
        result.id = Json.asString(data.get("id"));
        result.name = Json.asString(data.get("name"));
        result.editorType = Json.asString(data.get("editor_type"));
        result.templateEngine = Json.asString(data.get("template_engine"));
        result.globalSubstitutions = Json.asMapOf(data.get("global_substitutions"), Json::asString);
        result.globalMetadata = Json.asMapOf(data.get("global_metadata"), Json::asString);
        result.body = Json.asObject(data.get("body"), BodyObject::fromMap);
        result.subject = Json.asString(data.get("subject"));
        result.fromEmail = Json.asString(data.get("from_email"));
        result.fromName = Json.asString(data.get("from_name"));
        result.replyTo = Json.asString(data.get("reply_to"));
        result.replyToName = Json.asString(data.get("reply_to_name"));
        result.trackLinks = Json.asInteger(data.get("track_links"));
        result.trackRead = Json.asInteger(data.get("track_read"));
        result.headers = Json.asObject(data.get("headers"), HeadersObject::fromMap);
        result.attachments = Json.asList(data.get("attachments"), v0 -> Json.asObject(v0, AttachmentsArrayItem::fromMap));
        result.inlineAttachments = Json.asList(data.get("inline_attachments"), v0 -> Json.asObject(v0, InlineAttachmentsArrayItem::fromMap));
        result.created = Json.asString(data.get("created"));
        result.userId = Json.asInteger(data.get("user_id"));
        result.projectId = Json.asString(data.get("project_id"));
        result.projectName = Json.asString(data.get("project_name"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "id", id);
        Json.put(result, "name", name);
        Json.put(result, "editor_type", editorType);
        Json.put(result, "template_engine", templateEngine);
        Json.put(result, "global_substitutions", globalSubstitutions);
        Json.put(result, "global_metadata", globalMetadata);
        Json.put(result, "body", body == null ? null : body.toMap());
        Json.put(result, "subject", subject);
        Json.put(result, "from_email", fromEmail);
        Json.put(result, "from_name", fromName);
        Json.put(result, "reply_to", replyTo);
        Json.put(result, "reply_to_name", replyToName);
        Json.put(result, "track_links", trackLinks);
        Json.put(result, "track_read", trackRead);
        Json.put(result, "headers", headers == null ? null : headers.toMap());
        Json.put(result, "attachments", Json.mapList(attachments, v0 -> v0.toMap()));
        Json.put(result, "inline_attachments", Json.mapList(inlineAttachments, v0 -> v0.toMap()));
        Json.put(result, "created", created);
        Json.put(result, "user_id", userId);
        Json.put(result, "project_id", projectId);
        Json.put(result, "project_name", projectName);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
