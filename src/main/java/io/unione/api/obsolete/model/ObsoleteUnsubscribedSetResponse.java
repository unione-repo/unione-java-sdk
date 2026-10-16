package io.unione.api.obsolete.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** ObsoleteUnsubscribedSetResponse */
public class ObsoleteUnsubscribedSetResponse {
    /**
     * "success" string
     * Allowed values: `success`.
     */
    private String status;

    /** The unsubscribed email. */
    private String address;

    /**
     * Unsubscription process details:
     * <p>
     * - "unsubscribed" if email has just been unsubscribed,
     * - "was inactive" if it was inactive due some reason,
     * - "was unsubscribed" if it already was unsubscribed before the call,
     * - "added and unsubscribed" if it's a new email without status history and it was added and then unsubscribed immediately.
     * <p>
     * Raw value, kept also when the SDK does not know it.
     */
    private String message;

    public String getStatus() {
        return status;
    }

    public ObsoleteUnsubscribedSetResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ObsoleteUnsubscribedSetResponse setAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * Enum value of the field, UNKNOWN_VALUE for a value unknown to this SDK version (the raw one:
     * getMessageValue()), null when absent.
     */
    public ObsoleteUnsubscribedSetMessage getMessage() {
        return ObsoleteUnsubscribedSetMessage.fromValue(message);
    }

    /**
     * Sets the field; IllegalArgumentException for UNKNOWN_VALUE (an unknown value goes to
     * setMessageValue()).
     */
    public ObsoleteUnsubscribedSetResponse setMessage(ObsoleteUnsubscribedSetMessage message) {
        this.message = ObsoleteUnsubscribedSetMessage.toValue(message);
        return this;
    }

    /** Raw value of the field as sent by the API, also when the SDK does not know it. */
    public String getMessageValue() {
        return message;
    }

    /** Sets the raw value, e.g. one added to the API after this SDK version. */
    public ObsoleteUnsubscribedSetResponse setMessageValue(String message) {
        this.message = message;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static ObsoleteUnsubscribedSetResponse fromMap(Map<String, Object> data) {
        ObsoleteUnsubscribedSetResponse result = new ObsoleteUnsubscribedSetResponse();
        result.status = Json.asString(data.get("status"));
        result.address = Json.asString(data.get("address"));
        result.message = Json.asString(data.get("message"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "status", status);
        Json.put(result, "address", address);
        Json.put(result, "message", message);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
