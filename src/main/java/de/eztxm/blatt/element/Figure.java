package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Figure extends Tag<Figure> {

    public Figure(Component... children) {
        super("figure", children);
    }
}
