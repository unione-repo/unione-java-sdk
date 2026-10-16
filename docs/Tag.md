# Tag

[Back to the list of sections](../README.md)

This family of methods is used to manage user-defined tags. Tags are used to categorize letters based on your own criteria, e. g. to create a distinction between marketing and transactional letters or group together all notifications for a particular order type. UniOne allows you to assign tags to all outgoing emails, and the tags you’ve set may then be used to filter and analyze delivery statistics.

`io.unione.api.tag.TagApi`, `client.tag()`

| Method | Endpoint | Description |
|---|---|---|
| [`list`](#list) | `POST /tag/list.json` | This method returns the full list of user-defined tags. Does not receive any parameters. |
| [`delete`](#delete) | `POST /tag/delete.json` | Removes tag. |

## list

`POST /tag/list.json` · [Documentation](https://docs.unione.io/en/web-api-ref#tag-list)

This method returns the full list of user-defined tags. Does not receive any parameters.

### Request

The method has no parameters.

### Response: `TagListResponse`

| Field | Type | Description |
|---|---|---|
| `status` | `String` | "success" string (`success`) |
| `tags` | `List<TagsResponseObject>` | An array of objects containing data for user-defined tags. |

### Example

```java
import io.unione.UniOne;
import io.unione.UniOneClient;
import io.unione.api.tag.model.TagListResponse;

public class Example {
    public static void main(String[] args) {
        UniOneClient client = UniOne.client("YOUR_API_KEY");

        TagListResponse response = client.tag().list();
        System.out.println(response.getTags());
    }
}
```

## delete

`POST /tag/delete.json` · [Documentation](https://docs.unione.io/en/web-api-ref#tag-delete)

Removes tag.

### Request: `TagDeleteRequest`

| Field | Type | Required | Description |
|---|---|---|---|
| `tagId` | `Integer` | yes | Unique tag id. |

### Response: `SuccessResponse`

| Field | Type | Description |
|---|---|---|
| `status` | `String` | "success" string (`success`) |

### Example

```java
import io.unione.UniOne;
import io.unione.UniOneClient;
import io.unione.api.tag.model.TagDeleteRequest;
import io.unione.common.model.SuccessResponse;

public class Example {
    public static void main(String[] args) {
        UniOneClient client = UniOne.client("YOUR_API_KEY");

        SuccessResponse response = client.tag().delete(new TagDeleteRequest()
            .setTagId(54));
        System.out.println(response);
    }
}
```
