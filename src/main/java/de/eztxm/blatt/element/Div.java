package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Div extends Tag<Div> {

    public Div(Component... children) {
        super("div", children);
    }
}
