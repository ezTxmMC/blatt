package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Picture extends Tag<Picture> {

    public Picture(Component... children) {
        super("picture", children);
    }
}
