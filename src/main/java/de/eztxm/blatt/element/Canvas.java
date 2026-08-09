package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Canvas extends Tag<Canvas> {

    public Canvas(Component... children) {
        super("canvas", children);
    }

    public Canvas width(int width) {
        attribute("width", String.valueOf(width));
        return this;
    }

    public Canvas height(int height) {
        attribute("height", String.valueOf(height));
        return this;
    }
}
