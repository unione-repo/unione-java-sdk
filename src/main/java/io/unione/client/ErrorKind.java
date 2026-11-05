package io.unione.client;

/** What failed: the kind of an {@link ApiException}. */
public enum ErrorKind {
    /** The API returned an error (see {@link ApiException#getErrorCode()}, {@link ApiException#getHint()}). */
    API,
    /** The request could not be sent or the response could not be received. */
    NETWORK,
    /** The request timed out. */
    TIMEOUT,
    /** The response is not the JSON of the API (a proxy page, a broken body). */
    INVALID_RESPONSE,
    /** Found before sending (an empty API key, a required field missing): no request was sent. */
    INVALID_REQUEST
}
