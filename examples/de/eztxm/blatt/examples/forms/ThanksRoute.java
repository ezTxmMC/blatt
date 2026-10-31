package de.eztxm.blatt.examples.forms;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Text;
import de.eztxm.blatt.element.Anchor;
import de.eztxm.blatt.element.Heading;
import de.eztxm.blatt.element.Main;
import de.eztxm.blatt.element.Paragraph;
import de.eztxm.blatt.routing.Page;
import de.eztxm.blatt.routing.Route;

@Route("/contact/thanks")
public final class ThanksRoute implements Page {

    @Override
    public Component root() {
        return new Main(
            new Heading(1, new Text("Thanks!")),
            new Paragraph(new Text("Your message arrived.")),
            new Anchor(new Text("Back to the form")).href("/contact"));
    }
}
