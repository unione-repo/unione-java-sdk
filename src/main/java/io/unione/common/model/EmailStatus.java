package io.unione.common.model;

/** EmailStatus */
public enum EmailStatus {
    SENT("sent"),
    DELIVERED("delivered"),
    OPENED("opened"),
    CLICKED("clicked"),
    UNSUBSCRIBED("unsubscribed"),
    SUBSCRIBED("subscribed"),
    SOFT_BOUNCED("soft_bounced"),
    HARD_BOUNCED("hard_bounced"),
    SPAM("spam"),
    /**
     * A value this version of the SDK does not know (added to the API later): the model keeps the raw
     * value, see its get...Value() accessor.
     */
    UNKNOWN_VALUE(null);

    private final String value;

    EmailStatus(String value) {
        this.value = value;
    }

    /** Value sent to and received from the API, null for UNKNOWN_VALUE. */
    public String getValue() {
        return value;
    }

    /** Enum constant for a decoded JSON value: null for null, UNKNOWN_VALUE for a value unknown to the SDK. */
    public static EmailStatus fromValue(Object value) {
        if (value == null) {
            return null;
        }
        for (EmailStatus item : values()) {
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
    public static String toValue(EmailStatus item) {
        if (item == UNKNOWN_VALUE) {
            throw new IllegalArgumentException("EmailStatus.UNKNOWN_VALUE has no API value: "
                + "set the raw value with the set...Value() setter of the model");
        }
        return item == null ? null : item.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
