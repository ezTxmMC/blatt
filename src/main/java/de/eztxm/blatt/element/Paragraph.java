package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Paragraph extends Tag<Paragraph> {

    public Paragraph(Component... children) {
        super("p", children);
    }
}
