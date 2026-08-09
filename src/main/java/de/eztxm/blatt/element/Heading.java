package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Heading extends Tag<Heading> {

    public Heading(int level, Component... children) {
        super("h" + level, children);
    }
}
