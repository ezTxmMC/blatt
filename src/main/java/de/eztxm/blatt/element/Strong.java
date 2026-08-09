package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Strong extends Tag<Strong> {

    public Strong(Component... children) {
        super("strong", children);
    }
}
