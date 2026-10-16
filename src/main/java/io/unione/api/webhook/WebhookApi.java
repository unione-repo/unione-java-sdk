package io.unione.api.webhook;

import io.unione.api.webhook.model.WebhookCallback;
import io.unione.api.webhook.model.WebhookDeleteRequest;
import io.unione.api.webhook.model.WebhookGetRequest;
import io.unione.api.webhook.model.WebhookGetResponse;
import io.unione.api.webhook.model.WebhookListRequest;
import io.unione.api.webhook.model.WebhookListResponse;
import io.unione.api.webhook.model.WebhookSetRequest;
import io.unione.api.webhook.model.WebhookSetResponse;
import io.unione.client.ApiClient;
import io.unione.client.ApiException;
import io.unione.client.Json;
import io.unione.common.model.SuccessResponse;
import java.util.Map;

/**
 * UniOne lets you recieve notifications about email status changes or spam blocks. You provide an URL
 * and every time a watched event occurs we will notify you by submitting a request with JSON data to
 * this URL.
 * <p>
 * You can configure the format of webhook calls (JSON or gzipped JSON), which events you want to be
 * notified of, whether or not you let us groups several events in one request, do you want to receive
 * extra details like SMTP answer or recipient's user agent, etc.
 * <p>
 * Please note that your webhook handler should be able to process at least 5 parallel requests, up to
 * 100 events each (but you can configure the maximum number of parallel connections up to 100, which
 * is recommended).
 * <p>
 * If the URL of your webhook handler is not available (the response is not HTTP 200 OK within 3
 * seconds timeout), the system will repeat requests each 10 minutes during 24 hours, passing extra
 * parameter retry_count. If there were at least 10 events during last 24 hours and all the webhook
 * requests about these events have failed then we consider webhook handler broken and change it's
 * status to inactive automatically. A notification about that is sent to an email [configured in
 * control panel](https://cp.unione.io/en/user/alerting/technical/).
 * <p>
 * The format of webhook request and details of each event are described
 * [here](https://docs.unione.io/en/web-api-ref#callback-format).
 */
public class WebhookApi {
    private final ApiClient client;

    public WebhookApi(ApiClient client) {
        this.client = client;
    }

    /**
     * Sets or edits a [webhook](https://docs.unione.io/en/web-api-ref#webhook), i.e. an event notification
     * handler.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#webhook-set
     * @throws ApiException when the API returns an error
     */
    public WebhookSetResponse set(WebhookSetRequest request) {
        return WebhookSetResponse.fromMap(client.call("/webhook/set.json", request.toMap()));
    }

    /**
     * Gets properties of a [webhook](https://docs.unione.io/en/web-api-ref#webhook).
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#webhook-get
     * @throws ApiException when the API returns an error
     */
    public WebhookGetResponse get(WebhookGetRequest request) {
        return WebhookGetResponse.fromMap(client.call("/webhook/get.json", request.toMap()));
    }

    /**
     * List all or some [webhooks](https://docs.unione.io/en/web-api-ref#webhook) (event notification
     * handlers) of a user or a project.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#webhook-list
     * @throws ApiException when the API returns an error
     */
    public WebhookListResponse list(WebhookListRequest request) {
        return WebhookListResponse.fromMap(client.call("/webhook/list.json", request.toMap()));
    }

    /**
     * Deletes an event notification handler
     * <p>
     * You can also temporarily deactivate webhook without deleting it by setting it's status to "disabled"
     * in [webhook/set](https://docs.unione.io/en/web-api-ref#webhook-set) method.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#webhook-delete
     * @throws ApiException when the API returns an error
     */
    public SuccessResponse delete(WebhookDeleteRequest request) {
        return SuccessResponse.fromMap(client.call("/webhook/delete.json", request.toMap()));
    }

    /**
     * Webhook callback format description.
     * @param body raw body of the incoming request
     */
    public static WebhookCallback parseCallback(String body) {
        return WebhookCallback.fromMap(Json.asMap(Json.parse(body)));
    }
}
