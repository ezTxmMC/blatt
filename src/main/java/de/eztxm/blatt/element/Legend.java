package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Legend extends Tag<Legend> {

    public Legend(Component... children) {
        super("legend", children);
    }
}
