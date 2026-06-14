package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Element;

public final class Anchor extends Element {

    public Anchor(Component... children) {
        super("a", children);
    }

    public Anchor href(String href) {
        attribute("href", href);
        return this;
    }

    public Anchor target(String target) {
        attribute("target", target);
        return this;
    }
}
