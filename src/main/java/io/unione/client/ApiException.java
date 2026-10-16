package io.unione.client;

import java.util.Map;

/** Thrown when the API returns an error or the response can not be processed. */
public class ApiException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /** Longest part of a response body quoted in the message. */
    static final int MAX_QUOTE = 500;

    private final int httpStatus;
    private final Integer errorCode;
    private final transient Map<String, Object> responseBody;
    private final String rawBody;

    public ApiException(String message, int httpStatus, Integer errorCode, Map<String, Object> responseBody,
                        Throwable cause) {
        this(message, httpStatus, errorCode, responseBody, null, cause);
    }

    public ApiException(String message, int httpStatus, Integer errorCode, Map<String, Object> responseBody,
                        String rawBody, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.responseBody = responseBody;
        this.rawBody = rawBody;
    }

    /** HTTP status of the response, 0 when the request was not sent. */
    public int getHttpStatus() {
        return httpStatus;
    }

    /** API error code (the "code" field of the error response), null when absent. */
    public Integer getErrorCode() {
        return errorCode;
    }

    /** Decoded error response, null when the response was not a JSON object (see getRawBody()). */
    public Map<String, Object> getResponseBody() {
        return responseBody;
    }

    /** Raw text of the response (also when it is not JSON: an HTML page of a proxy, ...), null when none. */
    public String getRawBody() {
        return rawBody;
    }

    /** The text cut to MAX_QUOTE characters, for the messages. */
    static String quote(String text) {
        if (text == null) {
            return "";
        }
        return text.length() <= MAX_QUOTE ? text : text.substring(0, MAX_QUOTE) + "... (" + text.length() + " chars)";
    }
}
