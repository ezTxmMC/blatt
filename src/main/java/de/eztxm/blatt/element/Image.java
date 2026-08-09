package de.eztxm.blatt.element;

import de.eztxm.blatt.core.VoidTag;

public final class Image extends VoidTag<Image> {

    public Image(String src, String alt) {
        super("img");
        attribute("src", src);
        attribute("alt", alt);
    }

    public Image width(String width) {
        attribute("width", width);
        return this;
    }

    public Image width(int width) {
        return width(String.valueOf(width));
    }

    public Image height(String height) {
        attribute("height", height);
        return this;
    }

    public Image height(int height) {
        return height(String.valueOf(height));
    }

    public Image srcSet(String srcSet) {
        attribute("srcset", srcSet);
        return this;
    }

    public Image sizes(String sizes) {
        attribute("sizes", sizes);
        return this;
    }

    public Image loading(String loading) {
        attribute("loading", loading);
        return this;
    }

    public Image decoding(String decoding) {
        attribute("decoding", decoding);
        return this;
    }
}
