package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Element;

public final class Section extends Element {

    public Section(Component... children) {
        super("section", children);
    }
}
