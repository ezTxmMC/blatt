package de.eztxm.blatt.element;

import de.eztxm.blatt.core.VoidElement;

public final class Image extends VoidElement {

    public Image(String src, String alt) {
        super("img");
        attribute("src", src);
        attribute("alt", alt);
    }

    public Image width(String width) {
        attribute("width", width);
        return this;
    }

    public Image height(String height) {
        attribute("height", height);
        return this;
    }
}
