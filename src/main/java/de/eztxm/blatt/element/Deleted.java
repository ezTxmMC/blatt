package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Deleted extends Tag<Deleted> {

    public Deleted(Component... children) {
        super("del", children);
    }

    public Deleted cite(String cite) {
        attribute("cite", cite);
        return this;
    }

    public Deleted dateTime(String dateTime) {
        attribute("datetime", dateTime);
        return this;
    }
}
