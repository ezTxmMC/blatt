package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Quote extends Tag<Quote> {

    public Quote(Component... children) {
        super("q", children);
    }

    public Quote cite(String cite) {
        attribute("cite", cite);
        return this;
    }
}
