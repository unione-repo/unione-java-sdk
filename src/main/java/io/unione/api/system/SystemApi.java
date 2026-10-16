package io.unione.api.system;

import io.unione.api.system.model.SystemInfoResponse;
import io.unione.api.system.model.SystemPingResponse;
import io.unione.client.ApiClient;
import io.unione.client.ApiException;
import java.util.Map;

/** System operations. */
public class SystemApi {
    private final ApiClient client;

    public SystemApi(ApiClient client) {
        this.client = client;
    }

    /**
     * Checks API key validity and returns user ID.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#system-ping
     * @throws ApiException when the API returns an error
     */
    public SystemPingResponse ping() {
        return SystemPingResponse.fromMap(client.call("/system/ping.json", null));
    }

    /**
     * Gets user or project info by API key.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#system-info
     * @throws ApiException when the API returns an error
     */
    public SystemInfoResponse info() {
        return SystemInfoResponse.fromMap(client.call("/system/info.json", null));
    }
}
