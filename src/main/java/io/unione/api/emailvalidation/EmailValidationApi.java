package io.unione.api.emailvalidation;

import io.unione.api.emailvalidation.model.EmailValidationSingleRequest;
import io.unione.api.emailvalidation.model.EmailValidationSingleResponse;
import io.unione.client.ApiClient;
import io.unione.client.ApiException;
import java.util.Map;

/**
 * Email validation methods. As a result of the check, you’ll get detailed information on email
 * validity. It will help eliminate nonexistent emails, decrease the number of complaints, clean up the
 * subscribers list and eventually enhance your sender reputation score. For example, you may add
 * validation to a subscription form on your site to let users know they’ve just entered an invalid
 * address.
 */
public class EmailValidationApi {
    private final ApiClient client;

    public EmailValidationApi(ApiClient client) {
        this.client = client;
    }

    /**
     * A maximum of two concurrent requests are allowed; if you send more requests, an error will be
     * returned. Since the verification process should be completed promptly, we've set a time limit on
     * email check. If we encounter a slow email server that does not respond in 5 seconds, you may get an
     * “unknown” status.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#email-validation-single
     * @throws ApiException when the API returns an error
     */
    public EmailValidationSingleResponse single(EmailValidationSingleRequest request) {
        return EmailValidationSingleResponse.fromMap(client.call("/email-validation/single.json", request.toMap()));
    }
}
