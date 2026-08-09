package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Html extends Tag<Html> {

    public Html(Component... children) {
        super("html", children);
    }

    public Html lang(String lang) {
        attribute("lang", lang);
        return this;
    }
}
