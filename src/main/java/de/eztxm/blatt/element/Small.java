package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Small extends Tag<Small> {

    public Small(Component... children) {
        super("small", children);
    }
}
