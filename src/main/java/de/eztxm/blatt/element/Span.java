package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Span extends Tag<Span> {

    public Span(Component... children) {
        super("span", children);
    }
}
