# System

[Back to the list of sections](../README.md)

System operations.

`io.unione.api.system.SystemApi`, `client.system()`

| Method | Endpoint | Description |
|---|---|---|
| [`ping`](#ping) | `POST /system/ping.json` | Checks API key validity and returns user ID. |
| [`info`](#info) | `POST /system/info.json` | Gets user or project info by API key. |

## ping

`POST /system/ping.json` · [Documentation](https://docs.unione.io/en/web-api-ref#system-ping)

Checks API key validity and returns user ID.

### Request

The method has no parameters.

### Response: `SystemPingResponse`

| Field | Type | Description |
|---|---|---|
| `status` | `String` | "success" string (`success`) |
| `userId` | `Integer` | Unique user identifier. |

### Example

```java
import io.unione.UniOne;

public class Example {
    public static void main(String[] args) {
        var client = UniOne.client("YOUR_API_KEY");

        var response = client.system().ping();
        System.out.println(response.getUserId());
    }
}
```

## info

`POST /system/info.json` · [Documentation](https://docs.unione.io/en/web-api-ref#system-info)

Gets user or project info by API key.

### Request

The method has no parameters.

### Response: `SystemInfoResponse`

| Field | Type | Description |
|---|---|---|
| `status` | `String` | "success" string (`success`) |
| `userId` | `Integer` | Unique user identifier. |
| `email` | `String` | Email of the user. |
| `projectId` | `String` | Unqiue project identifier, ASCII string up to 36 characters long. Present only if the API key used for request is the project API key. |
| `projectName` | `String` | Project name, unique for user account. Present only if the API key used for request is the project API key. |
| `projectAccounting` | `ProjectAccountingObject` | Object with email counter properties. Present only if the API key used for request is the user API key. |
| `accounting` | `SystemInfoResponseAccounting` | Object with accounting period properties. Present only if the API key used for request is the user API key. |

### Example

```java
import io.unione.UniOne;

public class Example {
    public static void main(String[] args) {
        var client = UniOne.client("YOUR_API_KEY");

        var response = client.system().info();
        System.out.println(response.getUserId());
    }
}
```
