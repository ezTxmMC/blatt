package de.eztxm.blatt.examples.api;

import de.eztxm.blatt.routing.Endpoint;
import de.eztxm.blatt.routing.HttpMethod;
import de.eztxm.blatt.routing.Route;
import io.javalin.http.Context;

/** POST /api/items with form field {@code name}. */
@Route(value = "/api/items", method = HttpMethod.POST)
public final class CreateItemEndpoint implements Endpoint {

    @Override
    public void handle(Context ctx) {
        String name = ctx.formParam("name");
        if (name == null || name.isBlank()) {
            ctx.status(400).contentType("application/json").result(Json.error("name is required"));
            return;
        }

        Item item = ItemStore.create(name.strip());
        ctx.status(201)
            .header("Location", "/api/items/" + item.id())
            .contentType("application/json")
            .result(Json.item(item));
    }
}
