package io.unione.api.tag;

import io.unione.api.tag.model.TagDeleteRequest;
import io.unione.api.tag.model.TagListResponse;
import io.unione.client.ApiClient;
import io.unione.client.ApiException;
import io.unione.common.model.SuccessResponse;
import java.util.Map;

/**
 * This family of methods is used to manage user-defined tags. Tags are used to categorize letters
 * based on your own criteria, e. g. to create a distinction between marketing and transactional
 * letters or group together all notifications for a particular order type. UniOne allows you to assign
 * tags to all outgoing emails, and the tags you’ve set may then be used to filter and analyze delivery
 * statistics.
 */
public class TagApi {
    private final ApiClient client;

    public TagApi(ApiClient client) {
        this.client = client;
    }

    /**
     * This method returns the full list of user-defined tags. Does not receive any parameters.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#tag-list
     * @throws ApiException when the API returns an error
     */
    public TagListResponse list() {
        return TagListResponse.fromMap(client.call("/tag/list.json", null));
    }

    /**
     * Removes tag.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#tag-delete
     * @throws ApiException when the API returns an error
     */
    public SuccessResponse delete(TagDeleteRequest request) {
        return SuccessResponse.fromMap(client.call("/tag/delete.json", request.toMap()));
    }
}
