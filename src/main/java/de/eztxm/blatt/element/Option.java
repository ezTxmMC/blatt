package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Option extends Tag<Option> {

    public Option(Component... children) {
        super("option", children);
    }

    public Option value(String value) {
        attribute("value", value);
        return this;
    }

    public Option label(String label) {
        attribute("label", label);
        return this;
    }

    public Option selected() {
        attribute("selected", "");
        return this;
    }

    public Option disabled() {
        attribute("disabled", "");
        return this;
    }
}
