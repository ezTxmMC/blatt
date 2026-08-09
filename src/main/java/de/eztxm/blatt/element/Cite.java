package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Cite extends Tag<Cite> {

    public Cite(Component... children) {
        super("cite", children);
    }
}
