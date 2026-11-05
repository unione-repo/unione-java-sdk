package io.unione.client;

import java.util.Map;

/**
 * Thrown when the API returns an error, the request fails or is invalid, or the response can not be processed. The
 * message names the API method and, for an API error, the code, the HTTP status and how to fix it:
 * "email/send: Unknown recipient (API error 204, HTTP 400). Fix: ... See ...#api-errors".
 */
public class ApiException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /** Longest part of a response body quoted in the message. */
    static final int MAX_QUOTE = 500;

    private final int httpStatus;
    private final Integer errorCode;
    private final transient Map<String, Object> responseBody;
    private final String rawBody;
    private final String method;
    private final String hint;
    private final ErrorKind kind;
    private final String apiMessage;

    /**
     * An API error of an unknown method.
     *
     * @param message message
     * @param httpStatus HTTP status, 0 when the request was not sent
     * @param errorCode API error code, null when absent
     * @param responseBody decoded error response, null when none
     * @param cause cause, null when none
     */
    public ApiException(String message, int httpStatus, Integer errorCode, Map<String, Object> responseBody,
                        Throwable cause) {
        this(message, httpStatus, errorCode, responseBody, null, cause);
    }

    /**
     * An API error of an unknown method.
     *
     * @param message message
     * @param httpStatus HTTP status, 0 when the request was not sent
     * @param errorCode API error code, null when absent
     * @param responseBody decoded error response, null when none
     * @param rawBody raw text of the response, null when none
     * @param cause cause, null when none
     */
    public ApiException(String message, int httpStatus, Integer errorCode, Map<String, Object> responseBody,
                        String rawBody, Throwable cause) {
        this(message, httpStatus, errorCode, responseBody, rawBody, cause, "", null, ErrorKind.API);
    }

    /**
     * An error of an API method.
     *
     * @param message message
     * @param httpStatus HTTP status, 0 when the request was not sent
     * @param errorCode API error code, null when absent
     * @param responseBody decoded error response, null when none
     * @param rawBody raw text of the response, null when none
     * @param cause cause, null when none
     * @param method API method of the request, e.g. "email/send", empty when unknown
     * @param hint how to fix the API error, null when unknown
     * @param kind what failed
     */
    public ApiException(String message, int httpStatus, Integer errorCode, Map<String, Object> responseBody,
                        String rawBody, Throwable cause, String method, String hint, ErrorKind kind) {
        this(message, httpStatus, errorCode, responseBody, rawBody, cause, method, hint, kind, null);
    }

    /**
     * An error of an API method with the message text of the API.
     *
     * @param message message
     * @param httpStatus HTTP status, 0 when the request was not sent
     * @param errorCode API error code, null when absent
     * @param responseBody decoded error response, null when none
     * @param rawBody raw text of the response, null when none
     * @param cause cause, null when none
     * @param method API method of the request, e.g. "email/send", empty when unknown
     * @param hint how to fix the API error, null when unknown
     * @param kind what failed
     * @param apiMessage the "message" field of the error response as is, null when none
     */
    public ApiException(String message, int httpStatus, Integer errorCode, Map<String, Object> responseBody,
                        String rawBody, Throwable cause, String method, String hint, ErrorKind kind, String apiMessage) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.responseBody = responseBody;
        this.rawBody = rawBody;
        this.method = method == null ? "" : method;
        this.hint = hint;
        this.kind = kind == null ? ErrorKind.API : kind;
        this.apiMessage = apiMessage;
    }

    /**
     * HTTP status of the response.
     *
     * @return the status, 0 when the request was not sent
     */
    public int getHttpStatus() {
        return httpStatus;
    }

    /**
     * API error code (the "code" field of the error response).
     *
     * @return the code, null when absent
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * Decoded error response.
     *
     * @return the response, null when it was not a JSON object (see getRawBody())
     */
    public Map<String, Object> getResponseBody() {
        return responseBody;
    }

    /**
     * Raw text of the response (also when it is not JSON: an HTML page of a proxy, ...).
     *
     * @return the text, null when none
     */
    public String getRawBody() {
        return rawBody;
    }

    /**
     * API method of the request, e.g. "email/send".
     *
     * @return the method, empty when unknown
     */
    public String getMethod() {
        return method;
    }

    /**
     * How to fix the API error (the errors table of the docs).
     *
     * @return the hint, null when unknown
     */
    public String getHint() {
        return hint;
    }

    /**
     * The message of the API as is (the "message" field of the error response): getMessage() adds the method, the
     * code, the HTTP status and the hint and cuts a long text.
     *
     * @return the message, null when there was no API response or it had no message
     */
    public String getApiMessage() {
        return apiMessage;
    }

    /**
     * What failed.
     *
     * @return the kind of the error
     */
    public ErrorKind getKind() {
        return kind;
    }

    /**
     * A repeat of the request may succeed: a network error, a timeout, HTTP 429 or 5xx. A request cancelled by the
     * caller (the thread interrupted) is not.
     *
     * @return true when the request may be repeated
     */
    public boolean isRetryable() {
        if (getCause() instanceof InterruptedException) {
            return false;
        }
        switch (kind) {
            case NETWORK:
            case TIMEOUT:
                return true;
            case API:
            case INVALID_RESPONSE:
                return httpStatus == 429 || httpStatus >= 500;
            default:
                return false;
        }
    }

    /** The text cut to MAX_QUOTE characters, for the messages. */
    static String quote(String text) {
        if (text == null) {
            return "";
        }
        return text.length() <= MAX_QUOTE ? text : text.substring(0, MAX_QUOTE) + "... (" + text.length() + " chars)";
    }
}
