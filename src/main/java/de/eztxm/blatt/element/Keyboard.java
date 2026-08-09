package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Keyboard extends Tag<Keyboard> {

    public Keyboard(Component... children) {
        super("kbd", children);
    }
}
