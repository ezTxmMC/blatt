package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Summary extends Tag<Summary> {

    public Summary(Component... children) {
        super("summary", children);
    }
}
