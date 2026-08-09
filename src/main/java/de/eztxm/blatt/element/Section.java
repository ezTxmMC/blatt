package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Section extends Tag<Section> {

    public Section(Component... children) {
        super("section", children);
    }
}
