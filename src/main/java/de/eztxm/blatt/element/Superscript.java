package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Superscript extends Tag<Superscript> {

    public Superscript(Component... children) {
        super("sup", children);
    }
}
