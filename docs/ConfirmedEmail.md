# ConfirmedEmail

[Back to the list of sections](../README.md)

Manage email addresses confirmed for sandbox-domain sending. These methods are available only with a main account API key.

`io.unione.api.confirmedemail.ConfirmedEmailApi`, `client.confirmedEmail()`

| Method | Endpoint | Description |
|---|---|---|
| [`create`](#create) | `POST /confirmed-email/create.json` | Adds an email address and sends a confirmation link. |
| [`list`](#list) | `POST /confirmed-email/list.json` | Lists confirmed and unconfirmed email addresses for the account. |
| [`delete`](#delete) | `POST /confirmed-email/delete.json` | Deletes an email address from the account's confirmed email list. |

## create

`POST /confirmed-email/create.json` · [Documentation](https://docs.unione.io/en/web-api-ref#confirmed-email-create)

Adds an email address and sends a confirmation link.

### Request: `ConfirmedEmailRequest`

| Field | Type | Required | Description |
|---|---|---|---|
| `email` | `String` | yes | Email address to add or delete. |

### Response: `ConfirmedEmailCreateResponse`

| Field | Type | Description |
|---|---|---|
| `status` | `String` | "success" string (`success`) |
| `confirmedEmail` | `ConfirmedEmail` |  |

### Example

```java
import io.unione.UniOne;
import io.unione.api.confirmedemail.model.ConfirmedEmailRequest;

public class Example {
    public static void main(String[] args) {
        var client = UniOne.client("YOUR_API_KEY");

        var response = client.confirmedEmail().create(new ConfirmedEmailRequest()
            .setEmail("dev@example.com"));
        System.out.println(response.getConfirmedEmail());
    }
}
```

## list

`POST /confirmed-email/list.json` · [Documentation](https://docs.unione.io/en/web-api-ref#confirmed-email-list)

Lists confirmed and unconfirmed email addresses for the account.

### Request

The method has no parameters.

### Response: `ConfirmedEmailListResponse`

| Field | Type | Description |
|---|---|---|
| `status` | `String` | "success" string (`success`) |
| `confirmedEmails` | `List<ConfirmedEmail>` |  |

### Example

```java
import io.unione.UniOne;

public class Example {
    public static void main(String[] args) {
        var client = UniOne.client("YOUR_API_KEY");

        var response = client.confirmedEmail().list();
        System.out.println(response.getConfirmedEmails());
    }
}
```

## delete

`POST /confirmed-email/delete.json` · [Documentation](https://docs.unione.io/en/web-api-ref#confirmed-email-delete)

Deletes an email address from the account's confirmed email list.

### Request: `ConfirmedEmailRequest`

| Field | Type | Required | Description |
|---|---|---|---|
| `email` | `String` | yes | Email address to add or delete. |

### Response: `ConfirmedEmailDeleteResponse`

| Field | Type | Description |
|---|---|---|
| `status` | `String` | "success" string (`success`) |
| `message` | `String` |  |

### Example

```java
import io.unione.UniOne;
import io.unione.api.confirmedemail.model.ConfirmedEmailRequest;

public class Example {
    public static void main(String[] args) {
        var client = UniOne.client("YOUR_API_KEY");

        var response = client.confirmedEmail().delete(new ConfirmedEmailRequest()
            .setEmail("dev@example.com"));
        System.out.println(response.getMessage());
    }
}
```
