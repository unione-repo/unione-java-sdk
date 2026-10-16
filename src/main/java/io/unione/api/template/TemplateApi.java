package io.unione.api.template;

import io.unione.api.template.model.TemplateDeleteRequest;
import io.unione.api.template.model.TemplateGetRequest;
import io.unione.api.template.model.TemplateGetResponse;
import io.unione.api.template.model.TemplateListRequest;
import io.unione.api.template.model.TemplateListResponse;
import io.unione.api.template.model.TemplateSetRequest;
import io.unione.api.template.model.TemplateSetResponse;
import io.unione.client.ApiClient;
import io.unione.client.ApiException;
import io.unione.common.model.SuccessResponse;
import java.util.Map;

/**
 * You have the option of storing a template on the UniOne side and then using it by id, which is
 * highly recommended for sending repetitive messages or high volume traffic. UniOne supports two
 * different [template engines](https://docs.unione.io/en/template-engines).
 */
public class TemplateApi {
    private final ApiClient client;

    public TemplateApi(ApiClient client) {
        this.client = client;
    }

    /**
     * Creates or updates an email template.
     * <p>
     * Restrictions:
     * * The maximum request size is 10 MB.
     * * The number of templates is limited to 10000.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#template-set
     * @throws ApiException when the API returns an error
     */
    public TemplateSetResponse set(TemplateSetRequest request) {
        return TemplateSetResponse.fromMap(client.call("/template/set.json", request.toMap()));
    }

    /**
     * Gets template properties by it's id.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#template-get
     * @throws ApiException when the API returns an error
     */
    public TemplateGetResponse get(TemplateGetRequest request) {
        return TemplateGetResponse.fromMap(client.call("/template/get.json", request.toMap()));
    }

    /**
     * Returns the list of all or some templates of the account.
     * <p>
     * You can iterate thru large list using "offset" and "limit" parameters. When less than "limit"
     * templates are returned then the end of list is reached.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#template-list
     * @throws ApiException when the API returns an error
     */
    public TemplateListResponse list(TemplateListRequest request) {
        return TemplateListResponse.fromMap(client.call("/template/list.json", request.toMap()));
    }

    /**
     * Deletes a template by id.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#template-delete
     * @throws ApiException when the API returns an error
     */
    public SuccessResponse delete(TemplateDeleteRequest request) {
        return SuccessResponse.fromMap(client.call("/template/delete.json", request.toMap()));
    }
}
