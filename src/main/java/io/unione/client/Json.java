package io.unione.client;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Minimal JSON support (parse/stringify) and conversion helpers used by the models, so the SDK has no dependencies.
 * Objects are {@code Map<String, Object>}, arrays {@code List<Object>}, numbers {@code Long} or {@code Double}.
 */
public final class Json {
    private Json() {
    }

    // ---- parse -----------------------------------------------------------

    /** Parses JSON text. @throws IllegalArgumentException on invalid JSON. */
    public static Object parse(String text) {
        Parser parser = new Parser(text);
        parser.skipWhitespace();
        Object value = parser.readValue();
        parser.skipWhitespace();
        if (parser.pos != text.length()) {
            throw parser.error("unexpected trailing data");
        }
        return value;
    }

    private static final class Parser {
        private final String s;
        private int pos;

        Parser(String s) {
            this.s = s;
        }

        IllegalArgumentException error(String message) {
            return new IllegalArgumentException("Invalid JSON at " + pos + ": " + message);
        }

        void skipWhitespace() {
            while (pos < s.length() && Character.isWhitespace(s.charAt(pos))) {
                pos++;
            }
        }

        Object readValue() {
            if (pos >= s.length()) {
                throw error("unexpected end");
            }
            char c = s.charAt(pos);
            switch (c) {
                case '{':
                    return readObject();
                case '[':
                    return readArray();
                case '"':
                    return readString();
                case 't':
                    expect("true");
                    return Boolean.TRUE;
                case 'f':
                    expect("false");
                    return Boolean.FALSE;
                case 'n':
                    expect("null");
                    return null;
                default:
                    return readNumber();
            }
        }

        void expect(String word) {
            if (!s.startsWith(word, pos)) {
                throw error("expected " + word);
            }
            pos += word.length();
        }

        Map<String, Object> readObject() {
            Map<String, Object> map = new LinkedHashMap<>();
            pos++;
            skipWhitespace();
            if (pos < s.length() && s.charAt(pos) == '}') {
                pos++;
                return map;
            }
            while (true) {
                skipWhitespace();
                if (pos >= s.length() || s.charAt(pos) != '"') {
                    throw error("expected a key");
                }
                String key = readString();
                skipWhitespace();
                if (pos >= s.length() || s.charAt(pos) != ':') {
                    throw error("expected ':'");
                }
                pos++;
                skipWhitespace();
                map.put(key, readValue());
                skipWhitespace();
                if (pos >= s.length()) {
                    throw error("unexpected end");
                }
                char c = s.charAt(pos++);
                if (c == '}') {
                    return map;
                }
                if (c != ',') {
                    throw error("expected ',' or '}'");
                }
            }
        }

        List<Object> readArray() {
            List<Object> list = new ArrayList<>();
            pos++;
            skipWhitespace();
            if (pos < s.length() && s.charAt(pos) == ']') {
                pos++;
                return list;
            }
            while (true) {
                skipWhitespace();
                list.add(readValue());
                skipWhitespace();
                if (pos >= s.length()) {
                    throw error("unexpected end");
                }
                char c = s.charAt(pos++);
                if (c == ']') {
                    return list;
                }
                if (c != ',') {
                    throw error("expected ',' or ']'");
                }
            }
        }

        String readString() {
            StringBuilder sb = new StringBuilder();
            pos++;
            while (pos < s.length()) {
                char c = s.charAt(pos++);
                if (c == '"') {
                    return sb.toString();
                }
                if (c != '\\') {
                    sb.append(c);
                    continue;
                }
                if (pos >= s.length()) {
                    break;
                }
                char e = s.charAt(pos++);
                switch (e) {
                    case '"': sb.append('"'); break;
                    case '\\': sb.append('\\'); break;
                    case '/': sb.append('/'); break;
                    case 'b': sb.append('\b'); break;
                    case 'f': sb.append('\f'); break;
                    case 'n': sb.append('\n'); break;
                    case 'r': sb.append('\r'); break;
                    case 't': sb.append('\t'); break;
                    case 'u':
                        if (pos + 4 > s.length()) {
                            throw error("bad unicode escape");
                        }
                        sb.append((char) Integer.parseInt(s.substring(pos, pos + 4), 16));
                        pos += 4;
                        break;
                    default:
                        throw error("bad escape");
                }
            }
            throw error("unterminated string");
        }

        Object readNumber() {
            int start = pos;
            while (pos < s.length() && "+-0123456789.eE".indexOf(s.charAt(pos)) >= 0) {
                pos++;
            }
            String number = s.substring(start, pos);
            if (number.isEmpty()) {
                throw error("unexpected character");
            }
            try {
                if (number.contains(".") || number.contains("e") || number.contains("E")) {
                    return Double.parseDouble(number);
                }
                return Long.parseLong(number);
            } catch (NumberFormatException e) {
                try {
                    return Double.parseDouble(number);
                } catch (NumberFormatException e2) {
                    throw error("bad number " + number);
                }
            }
        }
    }

    // ---- stringify --------------------------------------------------------

    /** Serializes maps, lists, strings, numbers, booleans and null to JSON. */
    public static String stringify(Object value) {
        StringBuilder sb = new StringBuilder();
        write(sb, value);
        return sb.toString();
    }

    private static void write(StringBuilder sb, Object value) {
        if (value == null) {
            sb.append("null");
        } else if (value instanceof String) {
            writeString(sb, (String) value);
        } else if (value instanceof Number || value instanceof Boolean) {
            sb.append(value);
        } else if (value instanceof Map) {
            sb.append('{');
            boolean first = true;
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) value).entrySet()) {
                if (!first) {
                    sb.append(',');
                }
                first = false;
                writeString(sb, String.valueOf(entry.getKey()));
                sb.append(':');
                write(sb, entry.getValue());
            }
            sb.append('}');
        } else if (value instanceof Iterable) {
            sb.append('[');
            boolean first = true;
            for (Object item : (Iterable<?>) value) {
                if (!first) {
                    sb.append(',');
                }
                first = false;
                write(sb, item);
            }
            sb.append(']');
        } else {
            writeString(sb, value.toString());
        }
    }

    private static void writeString(StringBuilder sb, String s) {
        sb.append('"');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '"': sb.append("\\\""); break;
                case '\\': sb.append("\\\\"); break;
                case '\n': sb.append("\\n"); break;
                case '\r': sb.append("\\r"); break;
                case '\t': sb.append("\\t"); break;
                default:
                    if (c < 0x20) {
                        sb.append(String.format("\\u%04x", (int) c));
                    } else {
                        sb.append(c);
                    }
            }
        }
        sb.append('"');
    }

    // ---- conversion helpers used by the models -----------------------------

    /** String of a JSON value: objects and arrays as JSON text, null stays null. */
    public static String asString(Object value) {
        if (value instanceof Map || value instanceof List) {
            return stringify(value);
        }
        return value == null ? null : value.toString();
    }

    /** Integer of a JSON value; null for null, a non-number, a fraction or a value out of the int range. */
    public static Integer asInteger(Object value) {
        Long result = asLong(value);
        return result == null || result < Integer.MIN_VALUE || result > Integer.MAX_VALUE ? null : result.intValue();
    }

    /** Long of a JSON value; null for null, a non-number, a fraction or a value out of the long range. */
    public static Long asLong(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Long || value instanceof Integer || value instanceof Short || value instanceof Byte) {
            return ((Number) value).longValue();
        }
        if (value instanceof Boolean) {
            return ((Boolean) value) ? 1L : 0L;
        }
        String text = value.toString().trim();
        try {
            return Long.valueOf(text);
        } catch (NumberFormatException e) {
            Double number = asDouble(value);
            if (number == null || number != Math.rint(number) || number < -9.223372036854775E18
                || number > 9.223372036854775E18) {
                return null;
            }
            return number.longValue();
        }
    }

    /** Double of a JSON value; null for null or a non-number. */
    public static Double asDouble(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        try {
            double result = Double.parseDouble(value.toString().trim());
            return Double.isNaN(result) || Double.isInfinite(result) ? null : result;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /** Boolean of a JSON value: true/false, 1/0, "true"/"false", "1"/"0"; null for anything else. */
    public static Boolean asBoolean(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        if (value instanceof Number) {
            return ((Number) value).doubleValue() != 0;
        }
        String text = value.toString().trim();
        if ("true".equalsIgnoreCase(text) || "1".equals(text)) {
            return Boolean.TRUE;
        }
        if ("false".equalsIgnoreCase(text) || "0".equals(text) || text.isEmpty()) {
            return Boolean.FALSE;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> asMap(Object value) {
        return value instanceof Map ? (Map<String, Object>) value : null;
    }

    /** Converts a JSON object with a model converter, null stays null. */
    public static <T> T asObject(Object value, Function<Map<String, Object>, T> converter) {
        Map<String, Object> map = asMap(value);
        return map == null ? null : converter.apply(map);
    }

    /** Converts a JSON array element by element. */
    public static <T> List<T> asList(Object value, Function<Object, T> converter) {
        if (!(value instanceof List)) {
            return null;
        }
        List<T> result = new ArrayList<>();
        for (Object item : (List<?>) value) {
            result.add(converter.apply(item));
        }
        return result;
    }

    /** Converts the values of a JSON object. */
    public static <T> Map<String, T> asMapOf(Object value, Function<Object, T> converter) {
        Map<String, Object> map = asMap(value);
        if (map == null) {
            return null;
        }
        Map<String, T> result = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            result.put(entry.getKey(), converter.apply(entry.getValue()));
        }
        return result;
    }

    /** Maps the elements of a list (models to maps), null stays null. */
    public static <T> List<Object> mapList(List<T> list, Function<T, Object> converter) {
        if (list == null) {
            return null;
        }
        List<Object> result = new ArrayList<>();
        for (T item : list) {
            result.add(converter.apply(item));
        }
        return result;
    }

    /** Maps the values of a map (models to maps), null stays null. */
    public static <T> Map<String, Object> mapValues(Map<String, T> map, Function<T, Object> converter) {
        if (map == null) {
            return null;
        }
        Map<String, Object> result = new LinkedHashMap<>();
        for (Map.Entry<String, T> entry : map.entrySet()) {
            result.put(entry.getKey(), converter.apply(entry.getValue()));
        }
        return result;
    }

    /** Converts the elements of a list (enum constants to raw values and back), null stays null. */
    public static <T, R> List<R> convertList(List<T> list, Function<T, R> converter) {
        if (list == null) {
            return null;
        }
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(converter.apply(item));
        }
        return result;
    }

    /** Converts the values of a map (enum constants to raw values and back), null stays null. */
    public static <T, R> Map<String, R> convertMap(Map<String, T> map, Function<T, R> converter) {
        if (map == null) {
            return null;
        }
        Map<String, R> result = new LinkedHashMap<>();
        for (Map.Entry<String, T> entry : map.entrySet()) {
            result.put(entry.getKey(), converter.apply(entry.getValue()));
        }
        return result;
    }

    /** Puts a value into a JSON object unless it is null. */
    public static void put(Map<String, Object> target, String key, Object value) {
        if (value != null) {
            target.put(key, value);
        }
    }
}
