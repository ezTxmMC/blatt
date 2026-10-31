package de.eztxm.blatt.routing;

import io.javalin.http.Context;

/**
 * A raw request handler for any {@link HttpMethod}. Use it for responses that are
 * not HTML pages: redirects, JSON, form processing, and so on.
 */
@FunctionalInterface
public interface Endpoint {

    void handle(Context ctx) throws Exception;
}
