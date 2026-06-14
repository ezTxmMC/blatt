package de.eztxm.blatt.examples;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Text;
import de.eztxm.blatt.element.Button;
import de.eztxm.blatt.element.Heading;
import de.eztxm.blatt.element.Main;
import de.eztxm.blatt.element.Paragraph;
import de.eztxm.blatt.routing.Page;
import de.eztxm.blatt.routing.Route;

@Route("/")
public final class CounterRoute implements Page {

    @Override
    public Component root() {
        return new Main(
            new Heading(1, new Text("Counter")),
            new Paragraph(new Text("0")).id("count"),
            new Button(new Text("Increment")).onClick(e -> """
                    const count = document.getElementById('count');
                    count.textContent = ++count.textContent;
                    """)
        );
    }
}
