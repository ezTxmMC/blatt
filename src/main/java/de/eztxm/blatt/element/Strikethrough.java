package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Strikethrough extends Tag<Strikethrough> {

    public Strikethrough(Component... children) {
        super("s", children);
    }
}
