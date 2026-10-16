package io.unione.api.suppression;

import io.unione.api.suppression.model.SuppressionDeleteRequest;
import io.unione.api.suppression.model.SuppressionGetRequest;
import io.unione.api.suppression.model.SuppressionGetResponse;
import io.unione.api.suppression.model.SuppressionListRequest;
import io.unione.api.suppression.model.SuppressionListResponse;
import io.unione.api.suppression.model.SuppressionSetRequest;
import io.unione.client.ApiClient;
import io.unione.client.ApiException;
import io.unione.common.model.SuccessResponse;
import java.util.Map;

/**
 * Methods for managing suppression lists. An email can be added to suppression list because of
 * unsubscription, bounce or complain.
 */
public class SuppressionApi {
    private final ApiClient client;

    public SuppressionApi(ApiClient client) {
        this.client = client;
    }

    /**
     * Adds an email address to the suppression list. You can always remove this address from the
     * suppression list later using the
     * [suppression/delete](https://docs.unione.io/en/web-api-ref#suppression-delete) method.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#suppression-set
     * @throws ApiException when the API returns an error
     */
    public SuccessResponse set(SuppressionSetRequest request) {
        return SuccessResponse.fromMap(client.call("/suppression/set.json", request.toMap()));
    }

    /**
     * Gets a reason and date of email suppression.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#suppression-get
     * @throws ApiException when the API returns an error
     */
    public SuppressionGetResponse get(SuppressionGetRequest request) {
        return SuppressionGetResponse.fromMap(client.call("/suppression/get.json", request.toMap()));
    }

    /**
     * Returns a suppression list since provided date.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#suppression-list
     * @throws ApiException when the API returns an error
     */
    public SuppressionListResponse list(SuppressionListRequest request) {
        return SuppressionListResponse.fromMap(client.call("/suppression/list.json", request.toMap()));
    }

    /**
     * Deletes an email from suppression list (only records with is_deletable=true). If there are no
     * entries of this email in the suppression list, API error 3003 is returned. If such entries exist but
     * no one can be deleted, API error 3004 is returned. If daily limit of deletes exceeded, API error 906
     * is returned.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#suppression-delete
     * @throws ApiException when the API returns an error
     */
    public SuccessResponse delete(SuppressionDeleteRequest request) {
        return SuccessResponse.fromMap(client.call("/suppression/delete.json", request.toMap()));
    }
}
