package io.unione;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import io.unione.api.email.model.EmailSendRequest;
import io.unione.api.email.model.EmailSendResponse;
import io.unione.api.email.model.MessageObject;
import io.unione.api.email.model.MessageObjectRecipientsItem;
import io.unione.api.system.model.SystemInfoResponse;
import io.unione.client.ApiClient;
import io.unione.client.ApiException;
import io.unione.client.Configuration;
import io.unione.client.ErrorKind;
import io.unione.client.Json;
import io.unione.common.model.BodyObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** The client against a local mock of the API (an HttpServer on 127.0.0.1): no network, no real API. */
class UniOneClientTest {
    static final String API_KEY = "test-key";
    static final String SEND_OK = "{\"status\":\"success\",\"job_id\":\"1ZymBc-00041N-9X\","
        + "\"emails\":[\"alex@example.com\"],\"failed_emails\":{\"bad@example.com\":\"invalid\"}}";
    static final String API_ERROR = "{\"status\":\"error\",\"message\":\"Unknown recipient\",\"code\":204}";
    /** The JSON that request() must post. */
    static final String SEND_BODY = "{\"message\":{\"recipients\":[{\"email\":\"alex@example.com\","
        + "\"substitutions\":{\"to_name\":\"Alex\"}}],\"from_email\":\"shop@example.com\",\"subject\":\"Hello\","
        + "\"body\":{\"html\":\"<p>Hi</p>\"}}}";

    /** The API: records the requests, answers with the set status and body. */
    static final class Mock implements HttpHandler {
        final HttpServer server;
        final ExecutorService executor = Executors.newCachedThreadPool();
        final List<String> paths = Collections.synchronizedList(new ArrayList<String>());
        final List<String> queries = Collections.synchronizedList(new ArrayList<String>());
        final List<String> bodies = Collections.synchronizedList(new ArrayList<String>());
        final List<String> keys = Collections.synchronizedList(new ArrayList<String>());
        final List<String> contentTypes = Collections.synchronizedList(new ArrayList<String>());
        volatile int status = 200;
        volatile String response = "{\"status\":\"success\"}";
        volatile String responseType = "application/json";
        volatile long delayMillis;

        Mock() throws IOException {
            server = HttpServer.create(new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 0), 0);
            server.createContext("/", this);
            server.setExecutor(executor);
            server.start();
        }

        String baseUrl() {
            return "http://127.0.0.1:" + server.getAddress().getPort() + Configuration.BASE_PATH;
        }

        void reply(int status, String response) {
            this.status = status;
            this.response = response;
        }

        int count() {
            return paths.size();
        }

        void stop() {
            server.stop(0);
            executor.shutdownNow();
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            try {
                paths.add(exchange.getRequestURI().getPath());
                queries.add(exchange.getRequestURI().getRawQuery());
                keys.add(exchange.getRequestHeaders().getFirst("X-API-KEY"));
                contentTypes.add(exchange.getRequestHeaders().getFirst("Content-Type"));
                bodies.add(read(exchange.getRequestBody()));
                if (delayMillis > 0) {
                    Thread.sleep(delayMillis);
                }
                byte[] out = response.getBytes(StandardCharsets.UTF_8);
                exchange.getResponseHeaders().set("Content-Type", responseType);
                exchange.sendResponseHeaders(status, out.length);
                OutputStream stream = exchange.getResponseBody();
                stream.write(out);
                stream.close();
            } catch (InterruptedException | IOException e) {
                // the client gave up (timeout) or the server stops
            } finally {
                exchange.close();
            }
        }

        static String read(InputStream in) throws IOException {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];
            int n;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
            return new String(out.toByteArray(), StandardCharsets.UTF_8);
        }
    }

    Mock mock;

    @BeforeEach
    void start() throws IOException {
        mock = new Mock();
    }

    @AfterEach
    void stop() {
        mock.stop();
    }

    Configuration config(String apiKey) {
        return new Configuration(apiKey).setBaseUrl(mock.baseUrl()).setTimeout(Duration.ofSeconds(10));
    }

    UniOneClient client() {
        return new UniOneClient(config(API_KEY));
    }

    static EmailSendRequest request() {
        return request(withoutRecipients()
            .setRecipients(List.of(MessageObjectRecipientsItem.fromAddress("Alex <alex@example.com>"))));
    }

    static MessageObject withoutRecipients() {
        return new MessageObject()
            .setFromEmail("shop@example.com")
            .setSubject("Hello")
            .setBody(BodyObject.html("<p>Hi</p>"));
    }

    static EmailSendRequest request(MessageObject message) {
        return new EmailSendRequest().setMessage(message);
    }

    ApiException sendError() {
        return assertThrows(ApiException.class, () -> client().email().send(request()));
    }

    @Test
    void sendPostsTheRequestAndParsesTheResponse() {
        mock.reply(200, SEND_OK);
        EmailSendResponse response = client().email().send(request());

        assertEquals("1ZymBc-00041N-9X", response.getJobId());
        assertEquals(Arrays.asList("alex@example.com"), response.getEmails());
        assertEquals("invalid", response.getFailedEmails().get("bad@example.com"));
        assertEquals(1, mock.count());
        assertEquals(Configuration.BASE_PATH + "/email/send.json", mock.paths.get(0));
        assertEquals(API_KEY, mock.keys.get(0));
        assertTrue(mock.contentTypes.get(0).startsWith("application/json"));
        assertEquals("platform=" + ApiClient.PLATFORM, mock.queries.get(0));
        assertEquals(Json.parse(SEND_BODY), Json.parse(mock.bodies.get(0)));
    }

    @Test
    void integrationGoesToThePlatformBeforeTheSdk() {
        UniOneClient client = new UniOneClient(config(API_KEY).setPlatform("framework.1.0.0"));
        client.system().info();
        assertTrue(mock.queries.get(0).startsWith("platform=framework.1.0.0%2C"), mock.queries.get(0));
    }

    @Test
    void methodWithoutBodyParsesTheResponse() {
        mock.reply(200, "{\"status\":\"success\",\"user_id\":11,\"email\":\"user@example.com\"}");
        SystemInfoResponse info = client().system().info();

        assertEquals(Integer.valueOf(11), info.getUserId());
        assertEquals("user@example.com", info.getEmail());
        assertEquals(Configuration.BASE_PATH + "/system/info.json", mock.paths.get(0));
        assertEquals("{}", mock.bodies.get(0));
    }

    @Test
    void apiErrorHasTheCodeTheHintAndTheMessageOfTheApi() {
        mock.reply(400, API_ERROR);
        ApiException e = sendError();

        assertEquals(ErrorKind.API, e.getKind());
        assertFalse(e.isRetryable());
        assertEquals(Integer.valueOf(204), e.getErrorCode());
        assertEquals(400, e.getHttpStatus());
        assertEquals("email/send", e.getMethod());
        assertNotNull(e.getHint());
        assertFalse(e.getHint().isEmpty());
        assertEquals("Unknown recipient", e.getApiMessage());
        assertEquals(API_ERROR, e.getRawBody());
        assertEquals("error", e.getResponseBody().get("status"));
        assertTrue(e.getMessage().startsWith("email/send: "), e.getMessage());
        assertTrue(e.getMessage().contains("(API error 204, HTTP 400)"), e.getMessage());
        assertTrue(e.getMessage().contains("Fix: "), e.getMessage());
    }

    @Test
    void tooManyRequestsAndServerErrorsAreRetryable() {
        for (int status : new int[] {429, 503}) {
            mock.reply(status, "{\"status\":\"error\",\"message\":\"Try later\",\"code\":0}");
            ApiException e = sendError();
            assertEquals(ErrorKind.API, e.getKind());
            assertEquals(status, e.getHttpStatus());
            assertTrue(e.isRetryable(), "HTTP " + status);
        }
    }

    @Test
    void proxyPageIsAnInvalidResponse() {
        mock.responseType = "text/html";
        mock.reply(502, "<html><body>Bad Gateway</body></html>");
        ApiException e = sendError();
        assertEquals(ErrorKind.INVALID_RESPONSE, e.getKind());
        assertEquals(502, e.getHttpStatus());
        assertTrue(e.isRetryable());
        assertEquals("<html><body>Bad Gateway</body></html>", e.getRawBody());

        mock.reply(200, "<html>OK</html>");
        e = sendError();
        assertEquals(ErrorKind.INVALID_RESPONSE, e.getKind());
        assertFalse(e.isRetryable());
    }

    @Test
    void connectionFailureIsANetworkError() throws IOException {
        ServerSocket socket = new ServerSocket(0, 1, InetAddress.getByName("127.0.0.1"));
        int port = socket.getLocalPort();
        socket.close(); // nothing listens on the port
        Configuration unreachable = new Configuration(API_KEY)
            .setBaseUrl("http://127.0.0.1:" + port)
            .setTimeout(Duration.ofSeconds(5));
        UniOneClient client = new UniOneClient(unreachable);
        ApiException e = assertThrows(ApiException.class, () -> client.email().send(request()));
        assertEquals(ErrorKind.NETWORK, e.getKind());
        assertTrue(e.isRetryable());
        assertEquals(0, e.getHttpStatus());
        assertTrue(e.getMessage().startsWith("email/send: "), e.getMessage());
    }

    @Test
    void slowResponseIsATimeout() {
        mock.delayMillis = 3000;
        UniOneClient client = new UniOneClient(config(API_KEY).setTimeout(Duration.ofMillis(300)));
        ApiException e = assertThrows(ApiException.class, () -> client.email().send(request()));
        assertEquals(ErrorKind.TIMEOUT, e.getKind());
        assertTrue(e.isRetryable());
        assertTrue(e.getMessage().contains("0.3 s"), e.getMessage());
    }

    @Test
    void emptyApiKeyIsRejectedBeforeSending() {
        ApiException e = assertThrows(ApiException.class,
            () -> new UniOneClient(config("")).email().send(request()));
        assertEquals(ErrorKind.INVALID_REQUEST, e.getKind());
        assertFalse(e.isRetryable());
        assertEquals(0, mock.count());
    }

    @Test
    void missingRecipientsAreRejectedBeforeSending() {
        MessageObject[] messages = {
            withoutRecipients(),
            withoutRecipients().setRecipients(new ArrayList<MessageObjectRecipientsItem>()),
        };
        for (MessageObject message : messages) {
            ApiException e = assertThrows(ApiException.class, () -> client().email().send(request(message)));
            assertEquals(ErrorKind.INVALID_REQUEST, e.getKind());
            assertEquals("email/send: message.recipients is required", e.getMessage());
        }
        MessageObject noEmail = withoutRecipients()
            .setRecipients(Arrays.asList(new MessageObjectRecipientsItem().setEmail("")));
        ApiException e = assertThrows(ApiException.class, () -> client().email().send(request(noEmail)));
        assertEquals("email/send: message.recipients[0].email is required", e.getMessage());
        assertEquals(0, mock.count());
    }

    @Test
    void longApiMessageIsCutInTheMessageOnly() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < 600; i++) {
            text.append('x');
        }
        mock.reply(400, "{\"status\":\"error\",\"message\":\"" + text + "\",\"code\":204}");
        ApiException e = sendError();
        assertEquals(text.toString(), e.getApiMessage());
        assertFalse(e.getMessage().contains(text), e.getMessage());
        assertTrue(e.getMessage().contains("(600 chars)"), e.getMessage());
    }

    @Test
    void errorCodeAsAString() {
        mock.reply(400, "{\"status\":\"error\",\"message\":\"Unknown recipient\",\"code\":\"204\"}");
        assertEquals(Integer.valueOf(204), sendError().getErrorCode());

        mock.reply(400, "{\"status\":\"error\",\"message\":\"Unknown recipient\",\"code\":\"abc\"}");
        ApiException e = sendError();
        assertNull(e.getErrorCode());
        assertTrue(e.getMessage().contains("(HTTP 400)"), e.getMessage());
    }

    @Test
    void interruptedRequestIsNotRetryable() {
        mock.delayMillis = 3000;
        Thread.currentThread().interrupt();
        ApiException e;
        try {
            e = assertThrows(ApiException.class, () -> client().email().send(request()));
        } finally {
            assertTrue(Thread.interrupted(), "the interrupt flag is kept");
        }
        assertTrue(e.getCause() instanceof InterruptedException, String.valueOf(e.getCause()));
        assertFalse(e.isRetryable());
    }
}
