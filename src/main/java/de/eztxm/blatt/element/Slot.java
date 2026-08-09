package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Slot extends Tag<Slot> {

    public Slot(Component... children) {
        super("slot", children);
    }

    public Slot name(String name) {
        attribute("name", name);
        return this;
    }
}
