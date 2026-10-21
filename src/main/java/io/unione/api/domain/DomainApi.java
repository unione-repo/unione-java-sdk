package io.unione.api.domain;

import io.unione.api.domain.model.DomainDeleteRequest;
import io.unione.api.domain.model.DomainDeleteResponse;
import io.unione.api.domain.model.DomainGetDnsRecordsRequest;
import io.unione.api.domain.model.DomainGetDnsRecordsResponse;
import io.unione.api.domain.model.DomainGetSandboxDomainResponse;
import io.unione.api.domain.model.DomainListRequest;
import io.unione.api.domain.model.DomainListResponse;
import io.unione.api.domain.model.DomainValidateDkimRequest;
import io.unione.api.domain.model.DomainValidateDkimResponse;
import io.unione.api.domain.model.DomainValidateVerificationRecordRequest;
import io.unione.api.domain.model.DomainValidateVerificationRecordResponse;
import io.unione.client.ApiClient;
import io.unione.client.ApiException;
import java.util.Map;

/**
 * You can use these methods to setup sender domains and check their statuses. They might be useful in
 * case you want to automate adding domains for your own clients.
 * <p>
 * To add new sender domain thru API you should take following steps:
 * - Get DNS records with [domain/get-dns-records](https://docs.unione.io/en/web-api-ref#domain-get-dns-records) method and add them to your DNS provider.
 * - Every 5-10 minutes call [domain/list](https://docs.unione.io/en/web-api-ref#domain-list) method for your domain (or several domains) and check verification-record.status and dkim.status in the response. You can call it more often if you like, but please not more than once per minute.
 * - If verification-record.status="unconfirmed" then you should call [domain/validate-verification-record](https://docs.unione.io/en/web-api-ref#domain-validate-verification-record) to trigger verification record validation.
 * - If dkim.status="inactive" then start DKIM checking by calling [domain/validate-dkim](https://docs.unione.io/en/web-api-ref#domain-validate-dkim) (UniOne itself checks  DKIM every 30 minutes also)
 * - When you get verification-record.status="confirmed" and dkim.status="active" for the domain then you can stop checking it's status with domain/list and start using it as a sender domain.
 */
public class DomainApi {
    private final ApiClient client;

    public DomainApi(ApiClient client) {
        this.client = client;
    }

    /**
     * Returns info on domain DNS setup for using with UniOne.
     * <p>
     * In order to send emails you have to confirm that you own the domain that you are going to send
     * emails from and setup DKIM signature for the domain. This method allows you to register your domain
     * in UniOne and generate verification record and DKIM key, or return previously generated ones if
     * domain is already registered.
     * You should add then DNS records like this to all of the domains you want to confirm:
     * <p>
     * `&#64;             IN TXT "unione-validate-hash=483bb362ebdbeedd755cfb1d4d661"`
     * <p>
     * `us._domainkey IN TXT "v=DKIM1; k=rsa; p=MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDo7"`
     * <p>
     * `&#64;             IN TXT  "v=spf1 include:spf.unione.io ~all"`
     * <p>
     * `_dmarc        IN CNAME "example.com.dmarc.unione.io."`
     * <p>
     * - First record value should contain "verification-record" field returned by get-dns-records method "as is".
     * - Second record value should contain "dkim" field value after "v=DKIM1; k=rsa; p=". Mind the name of this record! "us._domainkey." prefix is important for DKIM selector of UniOne to work properly.
     * - Third record contains optional, but very recommended SPF entry. It is not returned by the method. Probably you already have one, then just add append include:spf.unione.io right after v=spf1.
     * - Fourth record is a CNAME of the "_dmarc" host to the "dmarc" field value: UniOne then publishes the DMARC policy of the domain. If the domain already has its own DMARC TXT record, keep it instead.
     * <p>
     * Syntax requirements of different DNS providers may differ:
     * - sometimes it's necesarry to put full domain name instead of &#64; sign.
     * - some providers require add TTL value (like 3600)
     * - some providers don't require quotes for enclosing values
     * Please check requirements of your provider and advice your users to do it.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#domain-get-dns-records
     * @throws ApiException when the API returns an error
     */
    public DomainGetDnsRecordsResponse getDnsRecords(DomainGetDnsRecordsRequest request) {
        return DomainGetDnsRecordsResponse.fromMap(client.call("/domain/get-dns-records.json", request.toMap()));
    }

    /**
     * Triggers verification record validation for domain.
     * <p>
     * If you get 200 OK then it means the validation process started succesfully, but it doesn't mean the
     * verification record is valid. You should check later verification-record.status field returned by
     * [domain/list](https://docs.unione.io/en/web-api-ref#domain-list) method to get the result. Please
     * read more [here](https://docs.unione.io/en/web-api-ref#domain).
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#domain-validate-verification-record
     * @throws ApiException when the API returns an error
     */
    public DomainValidateVerificationRecordResponse validateVerificationRecord(DomainValidateVerificationRecordRequest request) {
        return DomainValidateVerificationRecordResponse.fromMap(client.call("/domain/validate-verification-record.json", request.toMap()));
    }

    /**
     * Validates DKIM record for domain.
     * <p>
     * If you get 200 OK then it means the validation process started succesfully, but it doesn't mean the
     * DKIM record is valid. You should check later dkim.status field returned by
     * [domain/list](https://docs.unione.io/en/web-api-ref#domain-list) method to get the result. Please
     * read more [here](https://docs.unione.io/en/web-api-ref#domain).
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#domain-validate-dkim
     * @throws ApiException when the API returns an error
     */
    public DomainValidateDkimResponse validateDkim(DomainValidateDkimRequest request) {
        return DomainValidateDkimResponse.fromMap(client.call("/domain/validate-dkim.json", request.toMap()));
    }

    /**
     * Returns a list of all registered domains for user or project, together with statuses of last
     * validations.
     * <p>
     * Returns last status of ownership validation and DKIM validation for each domain. Read more
     * [here](https://docs.unione.io/en/web-api-ref#domain).
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#domain-list
     * @throws ApiException when the API returns an error
     */
    public DomainListResponse list(DomainListRequest request) {
        return DomainListResponse.fromMap(client.call("/domain/list.json", request.toMap()));
    }

    /**
     * Creates a sandbox domain for the account or returns the existing one.
     * <p>
     * Returns an account sandbox domain that is ready for use in the `from_email` field of
     * [email/send](https://docs.unione.io/en/web-api-ref#email-send). If the account has no sandbox
     * domain, UniOne creates one and returns it. Repeated calls return the same domain.
     * <p>
     * The sandbox domain is removed 31 days after it was created; the next call creates a new one. Up to
     * 100 emails a day can be sent from it (the default limit of the account).
     * <p>
     * The method is available only with an account API key. Project API keys cannot create sandbox
     * domains.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#domain-get-sandbox-domain
     * @throws ApiException when the API returns an error
     */
    public DomainGetSandboxDomainResponse getSandboxDomain() {
        return DomainGetSandboxDomainResponse.fromMap(client.call("/domain/get-sandbox-domain.json", null));
    }

    /**
     * Deletes a domain. Attention: you should use project's API key to delete a project's domain.
     * <p>
     * Deletes a domain.
     * <p>
     * Documentation: https://docs.unione.io/en/web-api-ref#domain-delete
     * @throws ApiException when the API returns an error
     */
    public DomainDeleteResponse delete(DomainDeleteRequest request) {
        return DomainDeleteResponse.fromMap(client.call("/domain/delete.json", request.toMap()));
    }
}
