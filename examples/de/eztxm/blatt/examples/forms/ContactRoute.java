package de.eztxm.blatt.examples.forms;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Text;
import de.eztxm.blatt.element.Button;
import de.eztxm.blatt.element.Form;
import de.eztxm.blatt.element.Heading;
import de.eztxm.blatt.element.Input;
import de.eztxm.blatt.element.Label;
import de.eztxm.blatt.element.Main;
import de.eztxm.blatt.routing.Page;
import de.eztxm.blatt.routing.Route;

/** GET /contact renders the form. The POST is handled by {@link ContactSubmitEndpoint}. */
@Route("/contact")
public final class ContactRoute implements Page {

    @Override
    public Component root() {
        return new Main(
            new Heading(1, new Text("Contact")),
            new Form(
                new Label(new Text("Name")).forId("name"),
                new Input("text").id("name").name("name").required().autocomplete("name"),
                new Label(new Text("Email")).forId("email"),
                new Input("email").id("email").name("email").autocomplete("email"),
                new Button(new Text("Send")).type("submit").cssClass("send"))
                .action("/contact")
                .method("post"))
            .cssClass("contact");
    }
}
