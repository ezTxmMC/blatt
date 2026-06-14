package de.eztxm.blatt.routing;

import de.eztxm.blatt.core.Text;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RouterTest {

    private static final Page STUB = () -> new Text("stub");

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
}
