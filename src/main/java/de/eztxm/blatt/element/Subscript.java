package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Subscript extends Tag<Subscript> {

    public Subscript(Component... children) {
        super("sub", children);
    }
}
