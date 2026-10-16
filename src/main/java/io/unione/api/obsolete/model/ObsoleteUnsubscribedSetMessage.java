package io.unione.api.obsolete.model;

/**
 * Unsubscription process details:
 * <p>
 * - "unsubscribed" if email has just been unsubscribed,
 * - "was inactive" if it was inactive due some reason,
 * - "was unsubscribed" if it already was unsubscribed before the call,
 * - "added and unsubscribed" if it's a new email without status history and it was added and then unsubscribed immediately.
 */
public enum ObsoleteUnsubscribedSetMessage {
    UNSUBSCRIBED("unsubscribed"),
    WAS_INACTIVE("was inactive"),
    WAS_UNSUBSCRIBED("was unsubscribed"),
    ADDED_AND_UNSUBSCRIBED("added and unsubscribed"),
    /**
     * A value this version of the SDK does not know (added to the API later): the model keeps the raw
     * value, see its get...Value() accessor.
     */
    UNKNOWN_VALUE(null);

    private final String value;

    ObsoleteUnsubscribedSetMessage(String value) {
        this.value = value;
    }

    /** Value sent to and received from the API, null for UNKNOWN_VALUE. */
    public String getValue() {
        return value;
    }

    /** Enum constant for a decoded JSON value: null for null, UNKNOWN_VALUE for a value unknown to the SDK. */
    public static ObsoleteUnsubscribedSetMessage fromValue(Object value) {
        if (value == null) {
            return null;
        }
        for (ObsoleteUnsubscribedSetMessage item : values()) {
            if (item.value != null && String.valueOf(item.value).equals(String.valueOf(value))) {
                return item;
            }
        }
        return UNKNOWN_VALUE;
    }

    /**
     * Value of a constant for the API, null for null.
     * @param item enum constant
     * @return value sent to the API
     * @throws IllegalArgumentException for UNKNOWN_VALUE: set the raw value instead
     */
    public static String toValue(ObsoleteUnsubscribedSetMessage item) {
        if (item == UNKNOWN_VALUE) {
            throw new IllegalArgumentException("ObsoleteUnsubscribedSetMessage.UNKNOWN_VALUE has no API value: "
                + "set the raw value with the set...Value() setter of the model");
        }
        return item == null ? null : item.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
