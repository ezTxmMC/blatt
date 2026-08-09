package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Inserted extends Tag<Inserted> {

    public Inserted(Component... children) {
        super("ins", children);
    }

    public Inserted cite(String cite) {
        attribute("cite", cite);
        return this;
    }

    public Inserted dateTime(String dateTime) {
        attribute("datetime", dateTime);
        return this;
    }
}
