package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Aside extends Tag<Aside> {

    public Aside(Component... children) {
        super("aside", children);
    }
}
