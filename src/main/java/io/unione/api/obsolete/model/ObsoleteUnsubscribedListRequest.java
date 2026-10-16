package io.unione.api.obsolete.model;

import io.unione.client.Json;
import java.util.LinkedHashMap;
import java.util.Map;

/** ObsoleteUnsubscribedListRequest */
public class ObsoleteUnsubscribedListRequest {
    /**
     * Date in the format YYYY-MM-DD to get all unsubscribed emails from the "date_from" to the present
     * day. If the parameter is not specified, the default period is today (from 00:00 UTC).
     */
    private String dateFrom;

    public String getDateFrom() {
        return dateFrom;
    }

    public ObsoleteUnsubscribedListRequest setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    /** Builds the object from decoded JSON. */
    public static ObsoleteUnsubscribedListRequest fromMap(Map<String, Object> data) {
        ObsoleteUnsubscribedListRequest result = new ObsoleteUnsubscribedListRequest();
        result.dateFrom = Json.asString(data.get("date_from"));
        return result;
    }

    /** JSON-ready map, null fields are omitted. */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        Json.put(result, "date_from", dateFrom);
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(toMap());
    }
}
