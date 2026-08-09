package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Head extends Tag<Head> {

    public Head(Component... children) {
        super("head", children);
    }
}
