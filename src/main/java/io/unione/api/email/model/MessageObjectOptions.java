package io.unione.api.email.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** Additional message options. */
public class MessageObjectOptions {
    /**
     * Date and time in "YYYY-MM-DD hh:mm:ss" format in the UTC timezone. Allows schedule sending up to 24
     * hours in advance.
     */
    private String sendAt;

    /** Custom unsubscribe link. Read more [here](https://docs.unione.io/en/unsubscribe-link). */
    private String unsubscribeUrl;

    /**
     * Backend-domain identifier to send message with. If custom_backend_id is not set, default one for
     * your account/project will be used. You can pay for one or more dedicated IPs and use this option.
     */
    private Integer customBackendId;

    /**
     * SMTP pool identifier to send message with. Usually you don't need to pass this parameter because
     * correct smtp_pool_id is selected automatically based on the default one for your account/project or
     * according to custom_backend_id parameter.
     */
    private String smtpPoolId;

    public String getSendAt() {
        return sendAt;
    }

    public MessageObjectOptions setSendAt(String sendAt) {
        this.sendAt = sendAt;
        return this;
    }

    public String getUnsubscribeUrl() {
        return unsubscribeUrl;
    }

    public MessageObjectOptions setUnsubscribeUrl(String unsubscribeUrl) {
        this.unsubscribeUrl = unsubscribeUrl;
        return this;
    }

    public Integer getCustomBackendId() {
        return customBackendId;
    }

    public MessageObjectOptions setCustomBackendId(Integer customBackendId) {
        this.customBackendId = customBackendId;
        return this;
    }

    public String getSmtpPoolId() {
        return smtpPoolId;
    }

    public MessageObjectOptions setSmtpPoolId(String smtpPoolId) {
        this.smtpPoolId = smtpPoolId;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static MessageObjectOptions fromMap(Map<String, Object> data) {
        MessageObjectOptions result = new MessageObjectOptions();
        result.sendAt = Json.asString(data.get("send_at"));
        result.unsubscribeUrl = Json.asString(data.get("unsubscribe_url"));
        result.customBackendId = Json.asInteger(data.get("custom_backend_id"));
        result.smtpPoolId = Json.asString(data.get("smtp_pool_id"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "send_at", sendAt);
        Json.put(result, "unsubscribe_url", unsubscribeUrl);
        Json.put(result, "custom_backend_id", customBackendId);
        Json.put(result, "smtp_pool_id", smtpPoolId);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
