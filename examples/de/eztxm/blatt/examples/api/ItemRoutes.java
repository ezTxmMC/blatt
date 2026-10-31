package de.eztxm.blatt.examples.api;

import de.eztxm.blatt.routing.Router;
import io.javalin.http.Context;

/**
 * Routes that need a path parameter are registered by hand, because one
 * {@code @Route} class maps to one path. Each verb gets its own handler.
 */
public final class ItemRoutes {

    private ItemRoutes() {}

    public static void register(Router router) {
        router.get("/api/items/{id}", ItemRoutes::show);
        router.put("/api/items/{id}", ItemRoutes::replace);
        router.patch("/api/items/{id}", ItemRoutes::update);
        router.delete("/api/items/{id}", ItemRoutes::delete);
    }

    private static void show(Context ctx) {
        withId(ctx, id -> ItemStore.find(id)
            .ifPresentOrElse(
                item -> json(ctx, 200, Json.item(item)),
                () -> notFound(ctx)));
    }

    /** PUT: replaces the item. Needs form fields {@code name} and {@code done}. */
    private static void replace(Context ctx) {
        withId(ctx, id -> {
            String name = ctx.formParam("name");
            if (name == null || name.isBlank()) {
                json(ctx, 400, Json.error("name is required"));
                return;
            }
            boolean done = Boolean.parseBoolean(ctx.formParam("done"));
            ItemStore.replace(id, name.strip(), done)
                .ifPresentOrElse(item -> json(ctx, 200, Json.item(item)), () -> notFound(ctx));
        });
    }

    /** PATCH: changes only the fields that are sent. */
    private static void update(Context ctx) {
        withId(ctx, id -> {
            var name = java.util.Optional.ofNullable(ctx.formParam("name")).map(String::strip).filter(s -> !s.isEmpty());
            var done = java.util.Optional.ofNullable(ctx.formParam("done")).map(Boolean::parseBoolean);
            ItemStore.update(id, name, done)
                .ifPresentOrElse(item -> json(ctx, 200, Json.item(item)), () -> notFound(ctx));
        });
    }

    private static void delete(Context ctx) {
        withId(ctx, id -> {
            if (ItemStore.delete(id)) {
                ctx.status(204);
            } else {
                notFound(ctx);
            }
        });
    }

    private static void withId(Context ctx, java.util.function.LongConsumer action) {
        try {
            action.accept(Long.parseLong(ctx.pathParam("id")));
        } catch (NumberFormatException e) {
            json(ctx, 400, Json.error("id must be a number"));
        }
    }

    private static void notFound(Context ctx) {
        json(ctx, 404, Json.error("item not found"));
    }

    private static void json(Context ctx, int status, String body) {
        ctx.status(status).contentType("application/json").result(body);
    }
}
