package de.eztxm.blatt.routing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RouteScannerTest {

    @Test
    void registers_annotated_pages_as_get_and_endpoints_with_their_method() {
        Router router = new Router();
        new RouteScanner("de.eztxm.blatt.routing.fixtures").scan(router);

        assertTrue(router.resolve("/").isPresent());
        assertTrue(router.resolveEntry(HttpMethod.POST, "/items").isPresent());
        assertFalse(router.resolveEntry(HttpMethod.GET, "/items").isPresent());
        assertEquals(HttpMethod.POST, router.resolveEntry(HttpMethod.POST, "/items").get().method());
    }
}
