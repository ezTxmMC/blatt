package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class HeadingGroup extends Tag<HeadingGroup> {

    public HeadingGroup(Component... children) {
        super("hgroup", children);
    }
}
