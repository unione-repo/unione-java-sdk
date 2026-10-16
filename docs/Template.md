# Template

[Back to the list of sections](../README.md)

You have the option of storing a template on the UniOne side and then using it by id, which is highly recommended for sending repetitive messages or high volume traffic. UniOne supports two different [template engines](https://docs.unione.io/en/template-engines).

`io.unione.api.template.TemplateApi`, `client.template()`

| Method | Endpoint | Description |
|---|---|---|
| [`set`](#set) | `POST /template/set.json` | Creates or updates an email template. |
| [`get`](#get) | `POST /template/get.json` | Gets template properties by it's id. |
| [`list`](#list) | `POST /template/list.json` | Returns the list of all or some templates of the account. |
| [`delete`](#delete) | `POST /template/delete.json` | Deletes a template by id. |

## set

`POST /template/set.json` · [Documentation](https://docs.unione.io/en/web-api-ref#template-set)

Creates or updates an email template.

Restrictions:
* The maximum request size is 10 MB.
* The number of templates is limited to 10000.

### Request: `TemplateSetRequest`

| Field | Type | Required | Description |
|---|---|---|---|
| `template` | `TemplateObject` | yes | Object with all the template properties. |

### Response: `TemplateSetResponse`

| Field | Type | Description |
|---|---|---|
| `status` | `String` | "success" string (`success`) |
| `template` | `TemplateObject` | Object with all the template properties. |

### Example

```java
import io.unione.UniOne;
import io.unione.api.template.model.EditorType;
import io.unione.api.template.model.TemplateObject;
import io.unione.api.template.model.TemplateSetRequest;
import io.unione.common.model.TemplateEngine;

public class Example {
    public static void main(String[] args) {
        var client = UniOne.client("YOUR_API_KEY");

        var response = client.template().set(new TemplateSetRequest()
            .setTemplate(new TemplateObject()
                    .setName("name")
                    .setEditorType(EditorType.HTML)
                    .setTemplateEngine(TemplateEngine.VELOCITY)
                    .setSubject("UniOne test email ")
                    .setFromEmail("user@example.com")
                    .setFromName("John Smith")
                    .setReplyTo("email@example.com")
                    .setReplyToName("John Smith")));
        System.out.println(response.getTemplate());
    }
}
```

## get

`POST /template/get.json` · [Documentation](https://docs.unione.io/en/web-api-ref#template-get)

Gets template properties by it's id.

### Request: `TemplateGetRequest`

| Field | Type | Required | Description |
|---|---|---|---|
| `id` | `String` | yes | Template id. |

### Response: `TemplateGetResponse`

| Field | Type | Description |
|---|---|---|
| `status` | `String` | "success" string (`success`) |
| `template` | `TemplateObject` | Object with all the template properties. |

### Example

```java
import io.unione.UniOne;
import io.unione.api.template.model.TemplateGetRequest;

public class Example {
    public static void main(String[] args) {
        var client = UniOne.client("YOUR_API_KEY");

        var response = client.template().get(new TemplateGetRequest()
            .setId("00000000-0000-0000-0000-000000000000"));
        System.out.println(response.getTemplate());
    }
}
```

## list

`POST /template/list.json` · [Documentation](https://docs.unione.io/en/web-api-ref#template-list)

Returns the list of all or some templates of the account.

You can iterate thru large list using "offset" and "limit" parameters. When less than "limit" templates are returned then the end of list is reached.

### Request: `TemplateListRequest`

| Field | Type | Required | Description |
|---|---|---|---|
| `limit` | `Integer` |  | Limits the number of templates returned, default is 50. |
| `offset` | `Integer` |  | Index of the first template in the list, starting with 0. |

### Response: `TemplateListResponse`

| Field | Type | Description |
|---|---|---|
| `status` | `String` | "success" string (`success`) |
| `templates` | `List<TemplateListObject>` | Array of template objects. |

### Example

```java
import io.unione.UniOne;
import io.unione.api.template.model.TemplateListRequest;

public class Example {
    public static void main(String[] args) {
        var client = UniOne.client("YOUR_API_KEY");

        var response = client.template().list(new TemplateListRequest());
        System.out.println(response.getTemplates());
    }
}
```

## delete

`POST /template/delete.json` · [Documentation](https://docs.unione.io/en/web-api-ref#template-delete)

Deletes a template by id.

### Request: `TemplateDeleteRequest`

| Field | Type | Required | Description |
|---|---|---|---|
| `id` | `String` | yes | Template id. |

### Response: `SuccessResponse`

| Field | Type | Description |
|---|---|---|
| `status` | `String` | "success" string (`success`) |

### Example

```java
import io.unione.UniOne;
import io.unione.api.template.model.TemplateDeleteRequest;

public class Example {
    public static void main(String[] args) {
        var client = UniOne.client("YOUR_API_KEY");

        var response = client.template().delete(new TemplateDeleteRequest()
            .setId("00000000-0000-0000-0000-000000000000"));
        System.out.println(response);
    }
}
```
