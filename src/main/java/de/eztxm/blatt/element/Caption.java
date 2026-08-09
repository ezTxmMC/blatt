package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Caption extends Tag<Caption> {

    public Caption(Component... children) {
        super("caption", children);
    }
}
