package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Output extends Tag<Output> {

    public Output(Component... children) {
        super("output", children);
    }

    public Output forId(String forId) {
        attribute("for", forId);
        return this;
    }

    public Output name(String name) {
        attribute("name", name);
        return this;
    }

    public Output form(String form) {
        attribute("form", form);
        return this;
    }
}
