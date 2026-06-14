package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Element;

public final class Html extends Element {

    public Html(Component... children) {
        super("html", children);
    }

    public Html lang(String lang) {
        attribute("lang", lang);
        return this;
    }
}
