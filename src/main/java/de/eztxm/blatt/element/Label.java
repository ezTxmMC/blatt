package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Label extends Tag<Label> {

    public Label(Component... children) {
        super("label", children);
    }

    public Label forId(String forId) {
        attribute("for", forId);
        return this;
    }

    public Label form(String form) {
        attribute("form", form);
        return this;
    }
}
