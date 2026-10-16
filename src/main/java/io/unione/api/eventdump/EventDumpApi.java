package io.unione.api.eventdump;

import io.unione.api.eventdump.model.EventDumpCreateRequest;
import io.unione.api.eventdump.model.EventDumpCreateResponse;
import io.unione.api.eventdump.model.EventDumpDeleteRequest;
import io.unione.api.eventdump.model.EventDumpGetRequest;
import io.unione.api.eventdump.model.EventDumpGetResponse;
import io.unione.api.eventdump.model.EventDumpListResponse;
import io.unione.client.ApiClient;
import io.unione.client.ApiException;
import io.unione.common.model.SuccessResponse;
import java.util.Map;

/**
 * These methods are used to obtain a list of events for a given period of time. The data is stored for
 * up to 45 days, depending on your tariff, i. e. you may fetch all event data starting from 00:00:00
 * 31 days ago and up to current time. For custom tarifs with big sending volumes event dumps are
 * available only for the last 7 days.
 */
public class EventDumpApi {
    private final ApiClient client;

    public EventDumpApi(ApiClient client) {
        this.client = client;
    }

    /**
     * This asynchronous method initiates the preparation of data dump in CSV format. The number of dump
     * files created or stored is limited to 10; to order more files, you have to delete one of the
     * previous ones or wait until they will be deleted automatically. A newly created dump file is kept
     * for 8 hours. If the request period is more than a day or there are too many events then several
     * files will be generated and each file will contain no more than 100,000 events. File contains
     * columns with the following information - 'event_time', 'job_id', 'from_email', 'email', 'status',
     * 'delivery_status', 'metadata', 'destination_response', 'user_agent', 'url', 'ip', 'tags' and
     * 'project_id' (for accounts with projects enabled). You can change the fields and include 'subject'
     * field using 'dump_fileds' parameter.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#event-dump-create
     * @throws ApiException when the API returns an error
     */
    public EventDumpCreateResponse create(EventDumpCreateRequest request) {
        return EventDumpCreateResponse.fromMap(client.call("/event-dump/create.json", request.toMap()));
    }

    /**
     * Returns dump properties for a given identifier
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#event-dump-get
     * @throws ApiException when the API returns an error
     */
    public EventDumpGetResponse get(EventDumpGetRequest request) {
        return EventDumpGetResponse.fromMap(client.call("/event-dump/get.json", request.toMap()));
    }

    /**
     * This method does not require any parameters; returns the full list of existing dumps.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#event-dump-list
     * @throws ApiException when the API returns an error
     */
    public EventDumpListResponse list() {
        return EventDumpListResponse.fromMap(client.call("/event-dump/list.json", null));
    }

    /**
     * Removes dump from queue or storage.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#event-dump-delete
     * @throws ApiException when the API returns an error
     */
    public SuccessResponse delete(EventDumpDeleteRequest request) {
        return SuccessResponse.fromMap(client.call("/event-dump/delete.json", request.toMap()));
    }
}
