package io.unione.api.email.model;

import io.unione.client.Json;
import io.unione.common.model.AttachmentsArrayItem;
import io.unione.common.model.BodyObject;
import io.unione.common.model.HeadersObject;
import io.unione.common.model.InlineAttachmentsArrayItem;
import io.unione.common.model.TemplateEngine;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Object containing all message properties. */
public class MessageObject {
    /** Contains recipient emails, substitutions(merge tags) and metadata. */
    private List<MessageObjectRecipientsItem> recipients;

    /**
     * Optional identifier of the template that had been created by
     * [template/set](https://docs.unione.io/en/web-api-ref#template-set) method. If template_id is passed
     * then fields from the template are used instead of missed email/send parameters. E.g. if "body"
     * parameter in email/send is omitted, "body" from template will be used. If "subject" in email/send is
     * omitted, "subject" from template will be used.
     */
    private String templateId;

    /**
     * An array of strings. The maximum string length is 100 characters. You can include up to 4 strings
     * which must be unique. No more than 10 000 tags are allowed per project; if you try to add more,
     * email/send will return an error. You may use tags to categorize emails by your own criteria, and
     * they will be passed along by event-dump methods.
     */
    private List<String> tags;

    /**
     * Whether to skip or not appending default unsubscribe footer. 1=skip, 0=append, default is 0. You
     * should [ask support](https://cp.unione.io/en/support) to approve
     * usage of skip_unsubscribe=1. [Read more](https://docs.unione.io/en/unsubscribe-link).
     * Allowed values: `0`, `1`.
     */
    private Integer skipUnsubscribe;

    /**
     * The language of the unsubscribe footer and unsubscribe page. Languages supported are: "be", "de",
     * "en", "es", "fr", "it", "pl", "pt", "ru", "ua", "kz".
     */
    private String globalLanguage;

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

    /** Sender's email. Required only if template_id parameter is empty. */
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
     * (allowed values 0 or 1, default 0) If set to 1, the global unavailability list will be ignored. Even
     * if the address was found to be unreachable while sending other UniOne users' emails, or its owner
     * has issued complaints, the message will still be sent. The setting may be ignored for certain
     * addresses.
     * Allowed values: `0`, `1`.
     */
    private Integer bypassGlobal;

    /**
     * (allowed values 0 or 1, default 0) If set to 1, the current user's or project's unavailability list
     * will be ignored. Works only if bypass_global is set to 1.
     * Allowed values: `0`, `1`.
     */
    private Integer bypassUnavailable;

    /**
     * (allowed values 0 or 1, default 0) If set to 1, the current list of unsubscribed addresses for this
     * account or project will be ignored. Works only if bypass_global is set to 1. The setting is
     * available only for users that have been granted the right to omit the unsubscribe link (to request,
     * please contact [support](https://cp.unione.io/en/support)).
     * Allowed values: `0`, `1`.
     */
    private Integer bypassUnsubscribed;

    /**
     * (allowed values 0 or 1, default 0) If set to 1, the user's or project's complaint list will be
     * ignored. Works only if bypass_global is set to 1. The setting is available only for users that have
     * been granted the right to omit the unsubscribe link (to request, please contact
     * [support](https://cp.unione.io/en/support)).
     * Allowed values: `0`, `1`.
     */
    private Integer bypassComplained;

    /**
     * A string of up to 64 characters containing a unique message key. This can be used to prevent
     * occasional message duplicates. If you send another API request with the same message key within the
     * next minute, it will be declined. We can generate a message key for each letter automatically; to
     * enable this option, please contact our [tech support](https://cp.unione.io/en/support).
     */
    private String idempotenceKey;

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

    /** Additional message options. */
    private MessageObjectOptions options;

    public List<MessageObjectRecipientsItem> getRecipients() {
        return recipients;
    }

    public MessageObject setRecipients(List<MessageObjectRecipientsItem> recipients) {
        this.recipients = recipients;
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }

    public MessageObject setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public MessageObject setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public Integer getSkipUnsubscribe() {
        return skipUnsubscribe;
    }

    public MessageObject setSkipUnsubscribe(Integer skipUnsubscribe) {
        this.skipUnsubscribe = skipUnsubscribe;
        return this;
    }

    public String getGlobalLanguage() {
        return globalLanguage;
    }

    public MessageObject setGlobalLanguage(String globalLanguage) {
        this.globalLanguage = globalLanguage;
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
    public MessageObject setTemplateEngine(TemplateEngine templateEngine) {
        this.templateEngine = TemplateEngine.toValue(templateEngine);
        return this;
    }

    /** Raw value of the field as sent by the API, also when the SDK does not know it. */
    public String getTemplateEngineValue() {
        return templateEngine;
    }

    /** Sets the raw value, e.g. one added to the API after this SDK version. */
    public MessageObject setTemplateEngineValue(String templateEngine) {
        this.templateEngine = templateEngine;
        return this;
    }

    public Map<String, String> getGlobalSubstitutions() {
        return globalSubstitutions;
    }

    public MessageObject setGlobalSubstitutions(Map<String, String> globalSubstitutions) {
        this.globalSubstitutions = globalSubstitutions;
        return this;
    }

    public Map<String, String> getGlobalMetadata() {
        return globalMetadata;
    }

    public MessageObject setGlobalMetadata(Map<String, String> globalMetadata) {
        this.globalMetadata = globalMetadata;
        return this;
    }

    public BodyObject getBody() {
        return body;
    }

    public MessageObject setBody(BodyObject body) {
        this.body = body;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public MessageObject setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public MessageObject setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
        return this;
    }

    public String getFromName() {
        return fromName;
    }

    public MessageObject setFromName(String fromName) {
        this.fromName = fromName;
        return this;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public MessageObject setReplyTo(String replyTo) {
        this.replyTo = replyTo;
        return this;
    }

    public String getReplyToName() {
        return replyToName;
    }

    public MessageObject setReplyToName(String replyToName) {
        this.replyToName = replyToName;
        return this;
    }

    public Integer getTrackLinks() {
        return trackLinks;
    }

    public MessageObject setTrackLinks(Integer trackLinks) {
        this.trackLinks = trackLinks;
        return this;
    }

    public Integer getTrackRead() {
        return trackRead;
    }

    public MessageObject setTrackRead(Integer trackRead) {
        this.trackRead = trackRead;
        return this;
    }

    public Integer getBypassGlobal() {
        return bypassGlobal;
    }

    public MessageObject setBypassGlobal(Integer bypassGlobal) {
        this.bypassGlobal = bypassGlobal;
        return this;
    }

    public Integer getBypassUnavailable() {
        return bypassUnavailable;
    }

    public MessageObject setBypassUnavailable(Integer bypassUnavailable) {
        this.bypassUnavailable = bypassUnavailable;
        return this;
    }

    public Integer getBypassUnsubscribed() {
        return bypassUnsubscribed;
    }

    public MessageObject setBypassUnsubscribed(Integer bypassUnsubscribed) {
        this.bypassUnsubscribed = bypassUnsubscribed;
        return this;
    }

    public Integer getBypassComplained() {
        return bypassComplained;
    }

    public MessageObject setBypassComplained(Integer bypassComplained) {
        this.bypassComplained = bypassComplained;
        return this;
    }

    public String getIdempotenceKey() {
        return idempotenceKey;
    }

    public MessageObject setIdempotenceKey(String idempotenceKey) {
        this.idempotenceKey = idempotenceKey;
        return this;
    }

    public HeadersObject getHeaders() {
        return headers;
    }

    public MessageObject setHeaders(HeadersObject headers) {
        this.headers = headers;
        return this;
    }

    public List<AttachmentsArrayItem> getAttachments() {
        return attachments;
    }

    public MessageObject setAttachments(List<AttachmentsArrayItem> attachments) {
        this.attachments = attachments;
        return this;
    }

    public List<InlineAttachmentsArrayItem> getInlineAttachments() {
        return inlineAttachments;
    }

    public MessageObject setInlineAttachments(List<InlineAttachmentsArrayItem> inlineAttachments) {
        this.inlineAttachments = inlineAttachments;
        return this;
    }

    public MessageObjectOptions getOptions() {
        return options;
    }

    public MessageObject setOptions(MessageObjectOptions options) {
        this.options = options;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static MessageObject fromMap(Map<String, Object> data) {
        MessageObject result = new MessageObject();
        result.recipients = Json.asList(data.get("recipients"), v0 -> Json.asObject(v0, MessageObjectRecipientsItem::fromMap));
        result.templateId = Json.asString(data.get("template_id"));
        result.tags = Json.asList(data.get("tags"), Json::asString);
        result.skipUnsubscribe = Json.asInteger(data.get("skip_unsubscribe"));
        result.globalLanguage = Json.asString(data.get("global_language"));
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
        result.bypassGlobal = Json.asInteger(data.get("bypass_global"));
        result.bypassUnavailable = Json.asInteger(data.get("bypass_unavailable"));
        result.bypassUnsubscribed = Json.asInteger(data.get("bypass_unsubscribed"));
        result.bypassComplained = Json.asInteger(data.get("bypass_complained"));
        result.idempotenceKey = Json.asString(data.get("idempotence_key"));
        result.headers = Json.asObject(data.get("headers"), HeadersObject::fromMap);
        result.attachments = Json.asList(data.get("attachments"), v0 -> Json.asObject(v0, AttachmentsArrayItem::fromMap));
        result.inlineAttachments = Json.asList(data.get("inline_attachments"), v0 -> Json.asObject(v0, InlineAttachmentsArrayItem::fromMap));
        result.options = Json.asObject(data.get("options"), MessageObjectOptions::fromMap);
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "recipients", Json.mapList(recipients, v0 -> v0.toMap()));
        Json.put(result, "template_id", templateId);
        Json.put(result, "tags", tags);
        Json.put(result, "skip_unsubscribe", skipUnsubscribe);
        Json.put(result, "global_language", globalLanguage);
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
        Json.put(result, "bypass_global", bypassGlobal);
        Json.put(result, "bypass_unavailable", bypassUnavailable);
        Json.put(result, "bypass_unsubscribed", bypassUnsubscribed);
        Json.put(result, "bypass_complained", bypassComplained);
        Json.put(result, "idempotence_key", idempotenceKey);
        Json.put(result, "headers", headers == null ? null : headers.toMap());
        Json.put(result, "attachments", Json.mapList(attachments, v0 -> v0.toMap()));
        Json.put(result, "inline_attachments", Json.mapList(inlineAttachments, v0 -> v0.toMap()));
        Json.put(result, "options", options == null ? null : options.toMap());
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
