package io.unione.api.project;

import io.unione.api.project.model.ProjectCreateRequest;
import io.unione.api.project.model.ProjectCreateResponse;
import io.unione.api.project.model.ProjectDeleteRequest;
import io.unione.api.project.model.ProjectListRequest;
import io.unione.api.project.model.ProjectListResponse;
import io.unione.api.project.model.ProjectUpdateRequest;
import io.unione.api.project.model.ProjectUpdateResponse;
import io.unione.client.ApiClient;
import io.unione.client.ApiException;
import io.unione.common.model.SuccessResponse;
import java.util.Map;

/**
 * Projects allow you to group several independent domains, webhooks, templates, and unsubscription
 * lists together. It's very useful if you have your own clients and manage their data from a single
 * UniOne account. Each project receives its own API key, and all API calls with that API key only deal
 * with the data of that project. This way, you can assign a separate project for each client and be
 * sure that they won't conflict; e.g. unsubscribing from one project won't affect another.
 * <p>
 * Projects methods are disabled by default due to antispam measures. To enable them, [contact
 * support](https://cp.unione.io/en/support).
 * <p>
 * Please note that sending domains are confirmed and used on per-project basis by default. But it's
 * possible to allow projects to send emails from domains confirmed for main account - just [ask
 * support](https://cp.unione.io/en/support) to enable it.
 */
public class ProjectApi {
    private final ApiClient client;

    public ProjectApi(ApiClient client) {
        this.client = client;
    }

    /**
     * Creates a new project.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#project-create
     * @throws ApiException when the API returns an error
     */
    public ProjectCreateResponse create(ProjectCreateRequest request) {
        return ProjectCreateResponse.fromMap(client.call("/project/create.json", request.toMap()));
    }

    /**
     * Updates a project.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#project-update
     * @throws ApiException when the API returns an error
     */
    public ProjectUpdateResponse update(ProjectUpdateRequest request) {
        return ProjectUpdateResponse.fromMap(client.call("/project/update.json", request.toMap()));
    }

    /**
     * Lists projects.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#project-list
     * @throws ApiException when the API returns an error
     */
    public ProjectListResponse list(ProjectListRequest request) {
        return ProjectListResponse.fromMap(client.call("/project/list.json", request.toMap()));
    }

    /**
     * Deletes a project.
     * <p>
     * The project will be moved to the "trash bin" before being permanently deleted. You can restore the
     * project by contacting support.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#project-delete
     * @throws ApiException when the API returns an error
     */
    public SuccessResponse delete(ProjectDeleteRequest request) {
        return SuccessResponse.fromMap(client.call("/project/delete.json", request.toMap()));
    }
}
