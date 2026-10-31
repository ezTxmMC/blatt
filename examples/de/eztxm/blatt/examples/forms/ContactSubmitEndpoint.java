package de.eztxm.blatt.examples.forms;

import de.eztxm.blatt.routing.Endpoint;
import de.eztxm.blatt.routing.HttpMethod;
import de.eztxm.blatt.routing.Route;
import io.javalin.http.Context;

/** POST /contact: validates the form and redirects (Post/Redirect/Get). */
@Route(value = "/contact", method = HttpMethod.POST)
public final class ContactSubmitEndpoint implements Endpoint {

    @Override
    public void handle(Context ctx) {
        String name = ctx.formParam("name");
        if (name == null || name.isBlank()) {
            ctx.status(400).result("Name is required. Go back and try again.");
            return;
        }

        System.out.println("Contact from " + name.strip() + " <" + ctx.formParam("email") + ">");
        ctx.redirect("/contact/thanks");
    }
}
