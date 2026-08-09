package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Bold extends Tag<Bold> {

    public Bold(Component... children) {
        super("b", children);
    }
}
