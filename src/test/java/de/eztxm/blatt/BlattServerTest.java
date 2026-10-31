package de.eztxm.blatt;

import de.eztxm.blatt.core.HeadConfig;
import de.eztxm.blatt.core.Text;
import de.eztxm.blatt.routing.HttpMethod;
import de.eztxm.blatt.routing.Router;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BlattServerTest {

    private final HttpClient client = HttpClient.newHttpClient();
    private BlattServer server;
    private String base;

    @BeforeEach
    void startServer() throws IOException {
        int port;
        try (ServerSocket socket = new ServerSocket(0)) {
            port = socket.getLocalPort();
        }

        Router router = new Router().tailwind();
        router.get("/", () -> new Text("home"));
        router.post("/items", ctx -> ctx.status(201).result("created " + ctx.formParam("name")));
        router.put("/items/{id}", ctx -> ctx.result("put " + ctx.pathParam("id")));
        router.patch("/items/{id}", ctx -> ctx.result("patch " + ctx.pathParam("id")));
        router.delete("/items/{id}", ctx -> ctx.status(204));
        router.get("/items/new", ctx -> ctx.result("literal new"));
        router.get("/items/{id}", ctx -> ctx.result("get " + ctx.pathParam("id")));

        server = new BlattServer(router, new HeadConfig().title("Test"), port);
        server.start();
        base = "http://localhost:" + port;
    }

    @AfterEach
    void stopServer() {
        server.stop();
    }

    @Test
    void page_is_rendered_as_html_with_tailwind_enabled() throws Exception {
        HttpResponse<String> response = send(HttpMethod.GET, "/");

        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("<!DOCTYPE html>"));
        assertTrue(response.body().contains("text/tailwindcss"));
        assertTrue(response.body().contains("home"));
    }

    @Test
    void each_method_reaches_its_own_handler() throws Exception {
        HttpResponse<String> post = client.send(
            HttpRequest.newBuilder(URI.create(base + "/items"))
                .POST(HttpRequest.BodyPublishers.ofString("name=box"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build(),
            HttpResponse.BodyHandlers.ofString());
        assertEquals(201, post.statusCode());
        assertEquals("created box", post.body());

        assertEquals("put 7", send(HttpMethod.PUT, "/items/7").body());
        assertEquals("patch 7", send(HttpMethod.PATCH, "/items/7").body());
        assertEquals(204, send(HttpMethod.DELETE, "/items/7").statusCode());
    }

    @Test
    void path_parameters_and_literal_precedence_work_over_http() throws Exception {
        assertEquals("get 42", send(HttpMethod.GET, "/items/42").body());
        assertEquals("literal new", send(HttpMethod.GET, "/items/new").body());
    }

    @Test
    void unregistered_method_on_a_path_is_not_found() throws Exception {
        assertEquals(404, send(HttpMethod.POST, "/items/42").statusCode());
        assertEquals(404, send(HttpMethod.GET, "/missing").statusCode());
    }

    private HttpResponse<String> send(HttpMethod method, String path) throws IOException, InterruptedException {
        HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.noBody();
        HttpRequest request = HttpRequest.newBuilder(URI.create(base + path))
            .method(method.name(), body)
            .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
