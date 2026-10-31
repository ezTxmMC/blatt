package de.eztxm.blatt.examples.api;

import de.eztxm.blatt.routing.Endpoint;
import de.eztxm.blatt.routing.Route;
import io.javalin.http.Context;

/** GET /api/items — annotation style: one class per route. */
@Route("/api/items")
public final class ListItemsEndpoint implements Endpoint {

    @Override
    public void handle(Context ctx) {
        ctx.contentType("application/json").result(Json.items(ItemStore.all()));
    }
}
