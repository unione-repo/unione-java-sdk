package io.unione;

import io.unione.api.confirmedemail.ConfirmedEmailApi;
import io.unione.api.domain.DomainApi;
import io.unione.api.email.EmailApi;
import io.unione.api.emailvalidation.EmailValidationApi;
import io.unione.api.eventdump.EventDumpApi;
import io.unione.api.obsolete.ObsoleteApi;
import io.unione.api.project.ProjectApi;
import io.unione.api.suppression.SuppressionApi;
import io.unione.api.system.SystemApi;
import io.unione.api.tag.TagApi;
import io.unione.api.template.TemplateApi;
import io.unione.api.webhook.WebhookApi;
import io.unione.client.ApiClient;
import io.unione.client.Configuration;

/**
 * Entry point of the UniOne API: one accessor per section.
 * <p>
 * UniOneClient client = UniOne.client("YOUR_API_KEY");
 * client.email().send(...);
 */
public class UniOneClient {
    private final ApiClient apiClient;
    private final EmailApi email;
    private final EmailValidationApi emailValidation;
    private final ConfirmedEmailApi confirmedEmail;
    private final TemplateApi template;
    private final WebhookApi webhook;
    private final SuppressionApi suppression;
    private final DomainApi domain;
    private final EventDumpApi eventDump;
    private final TagApi tag;
    private final ProjectApi project;
    private final SystemApi system;
    private final ObsoleteApi obsolete;

    public UniOneClient(String apiKey) {
        this(new Configuration(apiKey));
    }

    public UniOneClient(Configuration config) {
        this(new ApiClient(config));
    }

    public UniOneClient(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.email = new EmailApi(apiClient);
        this.emailValidation = new EmailValidationApi(apiClient);
        this.confirmedEmail = new ConfirmedEmailApi(apiClient);
        this.template = new TemplateApi(apiClient);
        this.webhook = new WebhookApi(apiClient);
        this.suppression = new SuppressionApi(apiClient);
        this.domain = new DomainApi(apiClient);
        this.eventDump = new EventDumpApi(apiClient);
        this.tag = new TagApi(apiClient);
        this.project = new ProjectApi(apiClient);
        this.system = new SystemApi(apiClient);
        this.obsolete = new ObsoleteApi(apiClient);
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    /** This group of methods is related with email sending. */
    public EmailApi email() {
        return email;
    }

    /**
     * Email validation methods. As a result of the check, you’ll get detailed information on email
     * validity. It will help eliminate nonexistent emails, decrease the number of complaints, clean up the
     * subscribers list and eventually enhance your sender reputation score. For example, you may add
     * validation to a subscription form on your site to let users know they’ve just entered an invalid
     * address.
     */
    public EmailValidationApi emailValidation() {
        return emailValidation;
    }

    /**
     * Manage email addresses confirmed for sandbox-domain sending. These methods are available only with a
     * main account API key.
     */
    public ConfirmedEmailApi confirmedEmail() {
        return confirmedEmail;
    }

    /**
     * You have the option of storing a template on the UniOne side and then using it by id, which is
     * highly recommended for sending repetitive messages or high volume traffic. UniOne supports two
     * different [template engines](https://docs.unione.io/en/template-engines).
     */
    public TemplateApi template() {
        return template;
    }

    /**
     * UniOne lets you recieve notifications about email status changes or spam blocks. You provide an URL
     * and every time a watched event occurs we will notify you by submitting a request with JSON data to
     * this URL.
     */
    public WebhookApi webhook() {
        return webhook;
    }

    /**
     * Methods for managing suppression lists. An email can be added to suppression list because of
     * unsubscription, bounce or complain.
     */
    public SuppressionApi suppression() {
        return suppression;
    }

    /**
     * You can use these methods to setup sender domains and check their statuses. They might be useful in
     * case you want to automate adding domains for your own clients.
     */
    public DomainApi domain() {
        return domain;
    }

    /**
     * These methods are used to obtain a list of events for a given period of time. The data is stored for
     * up to 45 days, depending on your tariff, i. e. you may fetch all event data starting from 00:00:00
     * 31 days ago and up to current time. For custom tarifs with big sending volumes event dumps are
     * available only for the last 7 days.
     */
    public EventDumpApi eventDump() {
        return eventDump;
    }

    /**
     * This family of methods is used to manage user-defined tags. Tags are used to categorize letters
     * based on your own criteria, e. g. to create a distinction between marketing and transactional
     * letters or group together all notifications for a particular order type. UniOne allows you to assign
     * tags to all outgoing emails, and the tags you’ve set may then be used to filter and analyze delivery
     * statistics.
     */
    public TagApi tag() {
        return tag;
    }

    /**
     * Projects allow you to group several independent domains, webhooks, templates, and unsubscription
     * lists together. It's very useful if you have your own clients and manage their data from a single
     * UniOne account. Each project receives its own API key, and all API calls with that API key only deal
     * with the data of that project. This way, you can assign a separate project for each client and be
     * sure that they won't conflict; e.g. unsubscribing from one project won't affect another.
     */
    public ProjectApi project() {
        return project;
    }

    /** System operations. */
    public SystemApi system() {
        return system;
    }

    /**
     * Obsolete methods dealing with unsubscribes. We recommend using the more flexible
     * [suppression](https://docs.unione.io/en/web-api-ref#suppression) methods instead. Note that you
     * can't resubscribe an unsubscribed recipient using WebAPI, but you can send him an email with
     * subscription link using email/subscribe method.
     */
    public ObsoleteApi obsolete() {
        return obsolete;
    }
}
