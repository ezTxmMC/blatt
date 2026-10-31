package de.eztxm.blatt.routing.fixtures;

import de.eztxm.blatt.routing.Endpoint;
import de.eztxm.blatt.routing.HttpMethod;
import de.eztxm.blatt.routing.Route;
import io.javalin.http.Context;

@Route(value = "/items", method = HttpMethod.POST)
public final class CreateItemEndpoint implements Endpoint {

    @Override
    public void handle(Context ctx) {
        ctx.status(201);
    }
}
