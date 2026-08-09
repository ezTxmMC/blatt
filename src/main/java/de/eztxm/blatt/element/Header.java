package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Header extends Tag<Header> {

    public Header(Component... children) {
        super("header", children);
    }
}
