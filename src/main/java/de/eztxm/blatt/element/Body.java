package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Body extends Tag<Body> {

    public Body(Component... children) {
        super("body", children);
    }
}
