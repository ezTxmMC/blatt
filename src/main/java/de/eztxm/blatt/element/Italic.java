package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Italic extends Tag<Italic> {

    public Italic(Component... children) {
        super("i", children);
    }
}
