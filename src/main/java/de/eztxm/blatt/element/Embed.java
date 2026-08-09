package de.eztxm.blatt.element;

import de.eztxm.blatt.core.VoidTag;

public final class Embed extends VoidTag<Embed> {

    public Embed() {
        super("embed");
    }

    public Embed src(String src) {
        attribute("src", src);
        return this;
    }

    public Embed type(String type) {
        attribute("type", type);
        return this;
    }

    public Embed width(int width) {
        attribute("width", String.valueOf(width));
        return this;
    }

    public Embed height(int height) {
        attribute("height", String.valueOf(height));
        return this;
    }
}
