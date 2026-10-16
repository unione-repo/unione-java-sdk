package io.unione.common.model;

/**
 * Sets the email counter mode. It allows you to choose whether the counter should reset automatically,
 * or remain permanent. The following values are acceptable: 'default' - the counter resets to zero
 * automatically at the beginning of each accounting period, 'permanent' - the counter never resets
 * automatically. The reset can be done only manually by the user in the request with the passes 0
 * value in the email_counter field. If the request doesn't contain the field or contains a null value,
 * the limit value doesn't change.
 */
public enum EmailCounterMode {
    DEFAULT("default"),
    PERMANENT("permanent"),
    /**
     * A value this version of the SDK does not know (added to the API later): the model keeps the raw
     * value, see its get...Value() accessor.
     */
    UNKNOWN_VALUE(null);

    private final String value;

    EmailCounterMode(String value) {
        this.value = value;
    }

    /** Value sent to and received from the API, null for UNKNOWN_VALUE. */
    public String getValue() {
        return value;
    }

    /** Enum constant for a decoded JSON value: null for null, UNKNOWN_VALUE for a value unknown to the SDK. */
    public static EmailCounterMode fromValue(Object value) {
        if (value == null) {
            return null;
        }
        for (EmailCounterMode item : values()) {
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
    public static String toValue(EmailCounterMode item) {
        if (item == UNKNOWN_VALUE) {
            throw new IllegalArgumentException("EmailCounterMode.UNKNOWN_VALUE has no API value: "
                + "set the raw value with the set...Value() setter of the model");
        }
        return item == null ? null : item.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
