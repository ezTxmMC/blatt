package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Details extends Tag<Details> {

    public Details(Component... children) {
        super("details", children);
    }

    public Details name(String name) {
        attribute("name", name);
        return this;
    }

    public Details open() {
        attribute("open", "");
        return this;
    }
}
