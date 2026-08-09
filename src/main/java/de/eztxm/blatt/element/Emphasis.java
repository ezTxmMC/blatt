package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Emphasis extends Tag<Emphasis> {

    public Emphasis(Component... children) {
        super("em", children);
    }
}
