package io.unione.api.confirmedemail;

import io.unione.api.confirmedemail.model.ConfirmedEmailCreateResponse;
import io.unione.api.confirmedemail.model.ConfirmedEmailDeleteResponse;
import io.unione.api.confirmedemail.model.ConfirmedEmailListResponse;
import io.unione.api.confirmedemail.model.ConfirmedEmailRequest;
import io.unione.client.ApiClient;
import io.unione.client.ApiException;
import java.util.Map;

/**
 * Manage email addresses confirmed for sandbox-domain sending. These methods are available only with a
 * main account API key.
 */
public class ConfirmedEmailApi {
    private final ApiClient client;

    public ConfirmedEmailApi(ApiClient client) {
        this.client = client;
    }

    /**
     * Adds an email address and sends a confirmation link.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#confirmed-email-create
     * @throws ApiException when the API returns an error
     */
    public ConfirmedEmailCreateResponse create(ConfirmedEmailRequest request) {
        return ConfirmedEmailCreateResponse.fromMap(client.call("/confirmed-email/create.json", request.toMap()));
    }

    /**
     * Lists confirmed and unconfirmed email addresses for the account.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#confirmed-email-list
     * @throws ApiException when the API returns an error
     */
    public ConfirmedEmailListResponse list() {
        return ConfirmedEmailListResponse.fromMap(client.call("/confirmed-email/list.json", null));
    }

    /**
     * Deletes an email address from the account's confirmed email list.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#confirmed-email-delete
     * @throws ApiException when the API returns an error
     */
    public ConfirmedEmailDeleteResponse delete(ConfirmedEmailRequest request) {
        return ConfirmedEmailDeleteResponse.fromMap(client.call("/confirmed-email/delete.json", request.toMap()));
    }
}
