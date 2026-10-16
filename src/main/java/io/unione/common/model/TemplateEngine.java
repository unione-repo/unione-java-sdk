package io.unione.common.model;

/**
 * The [template engine](https://docs.unione.io/en/template-engines) for handling the
 * substitutions(merge tags), "simple", "velocity", "liquid" or "none". Default value is "simple".
 */
public enum TemplateEngine {
    SIMPLE("simple"),
    VELOCITY("velocity"),
    LIQUID("liquid"),
    NONE("none"),
    /**
     * A value this version of the SDK does not know (added to the API later): the model keeps the raw
     * value, see its get...Value() accessor.
     */
    UNKNOWN_VALUE(null);

    private final String value;

    TemplateEngine(String value) {
        this.value = value;
    }

    /** Value sent to and received from the API, null for UNKNOWN_VALUE. */
    public String getValue() {
        return value;
    }

    /** Enum constant for a decoded JSON value: null for null, UNKNOWN_VALUE for a value unknown to the SDK. */
    public static TemplateEngine fromValue(Object value) {
        if (value == null) {
            return null;
        }
        for (TemplateEngine item : values()) {
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
    public static String toValue(TemplateEngine item) {
        if (item == UNKNOWN_VALUE) {
            throw new IllegalArgumentException("TemplateEngine.UNKNOWN_VALUE has no API value: "
                + "set the raw value with the set...Value() setter of the model");
        }
        return item == null ? null : item.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
