package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Element;

public final class Paragraph extends Element {

    public Paragraph(Component... children) {
        super("p", children);
    }
}
