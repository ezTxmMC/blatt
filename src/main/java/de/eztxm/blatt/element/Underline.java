package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Underline extends Tag<Underline> {

    public Underline(Component... children) {
        super("u", children);
    }
}
