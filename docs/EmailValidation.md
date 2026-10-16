# EmailValidation

[Back to the list of sections](../README.md)

Email validation methods. As a result of the check, you’ll get detailed information on email validity. It will help eliminate nonexistent emails, decrease the number of complaints, clean up the subscribers list and eventually enhance your sender reputation score. For example, you may add validation to a subscription form on your site to let users know they’ve just entered an invalid address.

`io.unione.api.emailvalidation.EmailValidationApi`, `client.emailValidation()`

| Method | Endpoint | Description |
|---|---|---|
| [`single`](#single) | `POST /email-validation/single.json` | A maximum of two concurrent requests are allowed; if you send more requests, an error will be returned. Since the verification process should be completed promptly, we've set a time limit on email check. If we encounter a slow email server that does not respond in 5 seconds, you may get an “unknown” status. |

## single

`POST /email-validation/single.json` · [Documentation](https://docs.unione.io/en/web-api-ref#email-validation-single)

A maximum of two concurrent requests are allowed; if you send more requests, an error will be returned. Since the verification process should be completed promptly, we've set a time limit on email check. If we encounter a slow email server that does not respond in 5 seconds, you may get an “unknown” status.

### Request: `EmailValidationSingleRequest`

| Field | Type | Required | Description |
|---|---|---|---|
| `email` | `String` | yes | Email address to be checked. |

### Response: `EmailValidationSingleResponse`

| Field | Type | Description |
|---|---|---|
| `status` | `String` | "success" string (`success`) |
| `email` | `String` | Email address to be checked. |
| `resultValue` | `String` | Possible statuses:<br><br>- "valid" - the address is valid,<br>- "invalid" - the address is invalid,<br>- "suspicious" - the address looks suspicious,<br>- "unknown" - could not perform validation, the domain’s mail server has not responded within the time limit. |
| `cause` | `String` | Possible statuses:<br><br>- "no_mx_record" - no MX record found for the target domain,<br>- "syntax_error" - the address syntax is invalid,<br>- "possible_typo" - the address is likely to have a typo,<br>- "mailbox_not_found" - the address does not exist,<br>- "global_suppression" - the address has been marked as unreachable due to multiple previous delivery errors,<br>- "disposable" - this is a disposable one-time email address which is usually valid for a few minutes only,<br>- "role" - the address is not likely to belong to an actual person, but rather to a certain business staff role,<br>- "abuse" - the address is known to be a source of a large number of complaints, sometimes issued automatically,<br>- "spamtrap" - this email is a spam trap, it is published openly but never used for actual emails. Sending messages to such addresses has a huge negative impact on reputation score,<br>- "smtp_connection_failed" - the domain’s SMTP server does not respond; the address may contain a typo. |
| `validity` | `Integer` | Validity score, from 0 to 100 (0 – invalid, 100 – valid). |
| `localPart` | `String` | Local part (everything preceding the @ sign). |
| `domain` | `String` | Domain name part. |
| `mxFound` | `Boolean` | True if the address’ domain does have an MX record, false if does not. |
| `mxRecord` | `String` | Preferred MX record for the domain. |
| `didYouMean` | `String` | For addresses which are likely to have typing errors (cause=possible_typo), a suggested variant with a fixed typo. |
| `processedAt` | `String` | Email check date and time, YYYY-MM-DD hh:mm:ss UTC. |

### Example

```java
import io.unione.UniOne;
import io.unione.api.emailvalidation.model.EmailValidationSingleRequest;

public class Example {
    public static void main(String[] args) {
        var client = UniOne.client("YOUR_API_KEY");

        var response = client.emailValidation().single(new EmailValidationSingleRequest()
            .setEmail("email@example.com"));
        System.out.println(response.getEmail());
    }
}
```
