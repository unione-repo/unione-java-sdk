package io.unione.api.email;

import io.unione.api.email.model.EmailSendRequest;
import io.unione.api.email.model.EmailSendResponse;
import io.unione.api.email.model.EmailSubscribeRequest;
import io.unione.client.ApiClient;
import io.unione.client.ApiException;
import io.unione.common.model.SuccessResponse;
import java.util.Map;

/** This group of methods is related with email sending. */
public class EmailApi {
    private final ApiClient client;

    public EmailApi(ApiClient client) {
        this.client = client;
    }

    /**
     * Sends an email or a bunch of emails
     * <p>
     * While sending you can provide substitutions (merge tags), use a template, turn on read or click
     * tracking, etc.
     * <p>
     * Restrictions:
     * * maximum request size is 10 MB
     * * maximum number of recipients is 500
     * * The symbol '/' is not allowed in attachment names
     * <p>
     * If the list of recipients contains invalid, non-existent, duplicate, or unsubscribed emails, those
     * emails will be returned in the output parameter failed_emails, and the email will be sent to all
     * other valid emails addresses. If all the emails addresses fall into failed_emails, sending will not
     * be carried out and API error 204 will be returned.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#email-send
     * @throws ApiException when the API returns an error
     */
    public EmailSendResponse send(EmailSendRequest request) {
        return EmailSendResponse.fromMap(client.call("/email/send.json", request.toMap()));
    }

    /**
     * Sends an email with (re)subscribe link.
     * <p>
     * This method allows subscription renewal by a former subscriber who has previously unsubscribed, or
     * who is blocked because of complaints. This method sends an email with a new subscription link to a
     * former subscriber. Once the recipient clicks the link, their subscription status is restored.
     * There is a default restriction that limits this method to being used once a day. If you need to
     * resubscribe more often, please ask support about it.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#email-subscribe
     * @throws ApiException when the API returns an error
     */
    public SuccessResponse subscribe(EmailSubscribeRequest request) {
        return SuccessResponse.fromMap(client.call("/email/subscribe.json", request.toMap()));
    }
}
