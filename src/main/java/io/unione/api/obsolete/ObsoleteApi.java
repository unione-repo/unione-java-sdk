package io.unione.api.obsolete;

import io.unione.api.obsolete.model.ObsoleteUnsubscribedCheckRequest;
import io.unione.api.obsolete.model.ObsoleteUnsubscribedCheckResponse;
import io.unione.api.obsolete.model.ObsoleteUnsubscribedListRequest;
import io.unione.api.obsolete.model.ObsoleteUnsubscribedListResponse;
import io.unione.api.obsolete.model.ObsoleteUnsubscribedSetRequest;
import io.unione.api.obsolete.model.ObsoleteUnsubscribedSetResponse;
import io.unione.client.ApiClient;
import io.unione.client.ApiException;
import java.util.Map;

/**
 * Obsolete methods dealing with unsubscribes. We recommend using the more flexible
 * [suppression](https://docs.unione.io/en/web-api-ref#suppression) methods instead. Note that you
 * can't resubscribe an unsubscribed recipient using WebAPI, but you can send him an email with
 * subscription link using email/subscribe method.
 */
public class ObsoleteApi {
    private final ApiClient client;

    public ObsoleteApi(ApiClient client) {
        this.client = client;
    }

    /**
     * Registers a new unsubscribed email.
     * <p>
     * After registering an email as unsubscribed all future attempts to send a message to this email will
     * be rejected. Please note that there's no way to remove an email from unsubscribed thru the API. But
     * you can send an email with a link to resubscribe using
     * [email/subscribe](https://docs.unione.io/en/web-api-ref#email-subscribe) method or remove
     * "unsubscribed" status manually on [Email Search page](https://cp.unione.io/en/user/email) in control
     * panel (but only a limited number of unsubscribed email per day can be removed).
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#unsubscribed-set
     * @throws ApiException when the API returns an error
     */
    public ObsoleteUnsubscribedSetResponse unsubscribedSet(ObsoleteUnsubscribedSetRequest request) {
        return ObsoleteUnsubscribedSetResponse.fromMap(client.call("/unsubscribed/set.json", request.toMap()));
    }

    /**
     * Checks if an email is unsubscribed.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#unsubscribed-check
     * @throws ApiException when the API returns an error
     */
    public ObsoleteUnsubscribedCheckResponse unsubscribedCheck(ObsoleteUnsubscribedCheckRequest request) {
        return ObsoleteUnsubscribedCheckResponse.fromMap(client.call("/unsubscribed/check.json", request.toMap()));
    }

    /**
     * Returns a list of all unsubscribed emails since provided date.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#unsubscribed-list
     * @throws ApiException when the API returns an error
     */
    public ObsoleteUnsubscribedListResponse unsubscribedList(ObsoleteUnsubscribedListRequest request) {
        return ObsoleteUnsubscribedListResponse.fromMap(client.call("/unsubscribed/list.json", request.toMap()));
    }
}
