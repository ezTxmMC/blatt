package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Element;

public final class Heading extends Element {

    public Heading(int level, Component... children) {
        super("h" + level, children);
    }
}
