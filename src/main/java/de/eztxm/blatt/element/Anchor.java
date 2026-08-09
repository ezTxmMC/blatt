package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Anchor extends Tag<Anchor> {

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

    public Anchor rel(String rel) {
        attribute("rel", rel);
        return this;
    }

    public Anchor download() {
        attribute("download", "");
        return this;
    }
}
