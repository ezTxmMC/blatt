package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Mark extends Tag<Mark> {

    public Mark(Component... children) {
        super("mark", children);
    }
}
