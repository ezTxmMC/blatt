package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class FigureCaption extends Tag<FigureCaption> {

    public FigureCaption(Component... children) {
        super("figcaption", children);
    }
}
