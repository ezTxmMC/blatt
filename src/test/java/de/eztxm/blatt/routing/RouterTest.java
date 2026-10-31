package de.eztxm.blatt.routing;

import de.eztxm.blatt.core.Text;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RouterTest {

    private static final Page STUB = () -> new Text("stub");
    private static final Endpoint NOOP = ctx -> {};

    @Test
    void resolves_registered_path() {
        Router router = new Router();
        router.register("/", STUB);
        assertTrue(router.resolve("/").isPresent());
    }

    @Test
    void returns_empty_for_unknown_path() {
        Router router = new Router();
        assertTrue(router.resolve("/missing").isEmpty());
    }

    @Test
    void resolves_correct_page_for_path() {
        Router router = new Router();
        Page home = () -> new Text("home");
        Page about = () -> new Text("about");
        router.register("/", home);
        router.register("/about", about);
        assertSame(about, router.resolve("/about").get());
    }

    @Test
    void does_not_match_partial_paths() {
        Router router = new Router();
        router.register("/foo", STUB);
        assertTrue(router.resolve("/foobar").isEmpty());
    }

    @Test
    void resolves_first_registered_when_duplicate_paths() {
        Router router = new Router();
        Page first = () -> new Text("first");
        Page second = () -> new Text("second");
        router.register("/", first);
        router.register("/", second);
        assertSame(first, router.resolve("/").get());
    }

    @Test
    void same_path_can_be_registered_per_method() {
        Router router = new Router();
        Page show = () -> new Text("show");
        Page update = () -> new Text("update");
        router.get("/items/1", show);
        router.put("/items/1", update);

        assertSame(show, router.resolve(HttpMethod.GET, "/items/1").get());
        assertSame(update, router.resolve(HttpMethod.PUT, "/items/1").get());
        assertTrue(router.resolve(HttpMethod.POST, "/items/1").isEmpty());
    }

    @Test
    void get_is_the_default_for_resolve() {
        Router router = new Router();
        router.post("/form", STUB);
        assertTrue(router.resolve("/form").isEmpty());
        assertTrue(router.resolve(HttpMethod.POST, "/form").isPresent());
    }

    @Test
    void every_http_method_has_its_own_registration() {
        Router router = new Router();
        router.get("/r", NOOP);
        router.post("/r", NOOP);
        router.put("/r", NOOP);
        router.patch("/r", NOOP);
        router.delete("/r", NOOP);

        for (HttpMethod method : HttpMethod.values()) {
            RouteEntry entry = router.resolveEntry(method, "/r").orElseThrow();
            assertEquals(method, entry.method());
        }
        assertEquals(5, router.routes().size());
    }

    @Test
    void endpoints_are_not_returned_as_pages() {
        Router router = new Router();
        RouteEntry entry = router.delete("/item", NOOP);

        assertTrue(entry.endpoint().isPresent());
        assertTrue(entry.page().isEmpty());
        assertTrue(router.resolve(HttpMethod.DELETE, "/item").isEmpty());
        assertTrue(router.resolveEntry(HttpMethod.DELETE, "/item").isPresent());
    }

    @Test
    void first_registration_wins_per_method_and_path() {
        Router router = new Router();
        Endpoint first = ctx -> {};
        Endpoint second = ctx -> {};
        RouteEntry firstEntry = router.patch("/p", first);
        router.patch("/p", second);

        assertSame(firstEntry, router.resolveEntry(HttpMethod.PATCH, "/p").get());
        assertEquals(2, router.routes().size());
    }

    @Test
    void routes_keep_registration_order() {
        Router router = new Router();
        router.get("/a", STUB);
        router.post("/b", NOOP);
        router.get("/c", STUB);

        List<String> paths = router.routes().stream().map(RouteEntry::path).toList();
        assertEquals(List.of("/a", "/b", "/c"), paths);
    }

    @Test
    void route_entry_requires_a_page_or_endpoint() {
        assertThrows(IllegalArgumentException.class,
            () -> new RouteEntry("/x", HttpMethod.GET, (Page) null));
    }

    @Test
    void tailwind_is_disabled_by_default_and_can_be_enabled_per_route() {
        Router router = new Router();
        RouteEntry plain = router.register("/plain", STUB);
        RouteEntry styled = router.register("/styled", STUB).tailwind("@theme { --color-brand: #4338ca; }");

        assertFalse(plain.usesTailwind());
        assertTrue(styled.usesTailwind());
        assertEquals(List.of("@theme { --color-brand: #4338ca; }"), styled.tailwindCss());
        assertFalse(router.usesTailwind());

        router.tailwind();
        assertTrue(router.usesTailwind());
    }

    @Test
    void parameter_segment_matches_one_non_empty_segment() {
        Router router = new Router();
        Page item = () -> new Text("item");
        router.get("/items/{id}", item);

        assertSame(item, router.resolve("/items/42").get());
        assertTrue(router.resolve("/items/").isEmpty());
        assertTrue(router.resolve("/items/42/extra").isEmpty());
        assertTrue(router.resolve("/items").isEmpty());
    }

    @Test
    void parameter_segments_can_be_mixed_with_literals() {
        Router router = new Router();
        router.get("/users/{userId}/posts/{postId}", STUB);

        assertTrue(router.resolve("/users/7/posts/9").isPresent());
        assertTrue(router.resolve("/users/7/comments/9").isEmpty());
    }

    @Test
    void literal_path_wins_over_parameterized_path() {
        Router router = new Router();
        Page param = () -> new Text("param");
        Page literal = () -> new Text("literal");
        router.get("/items/{id}", param);
        router.get("/items/new", literal);

        assertSame(literal, router.resolve("/items/new").get());
        assertSame(param, router.resolve("/items/5").get());
    }

    @Test
    void parameterized_routes_are_scoped_to_their_method() {
        Router router = new Router();
        router.delete("/items/{id}", NOOP);

        assertTrue(router.resolveEntry(HttpMethod.DELETE, "/items/3").isPresent());
        assertTrue(router.resolveEntry(HttpMethod.GET, "/items/3").isEmpty());
    }
}
