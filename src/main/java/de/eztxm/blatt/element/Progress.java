package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Progress extends Tag<Progress> {

    public Progress(Component... children) {
        super("progress", children);
    }

    public Progress value(String value) {
        attribute("value", value);
        return this;
    }

    public Progress max(String max) {
        attribute("max", max);
        return this;
    }
}
