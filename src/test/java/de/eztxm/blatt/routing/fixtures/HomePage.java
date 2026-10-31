package de.eztxm.blatt.routing.fixtures;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Text;
import de.eztxm.blatt.routing.Page;
import de.eztxm.blatt.routing.Route;

@Route("/")
public final class HomePage implements Page {

    @Override
    public Component root() {
        return new Text("home");
    }
}
