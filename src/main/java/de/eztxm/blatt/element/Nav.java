package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Nav extends Tag<Nav> {

    public Nav(Component... children) {
        super("nav", children);
    }
}
