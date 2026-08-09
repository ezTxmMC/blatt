package de.eztxm.blatt.element;

import de.eztxm.blatt.core.VoidTag;

public final class Source extends VoidTag<Source> {

    public Source() {
        super("source");
    }

    public Source src(String src) {
        attribute("src", src);
        return this;
    }

    public Source type(String type) {
        attribute("type", type);
        return this;
    }

    public Source srcSet(String srcSet) {
        attribute("srcset", srcSet);
        return this;
    }

    public Source sizes(String sizes) {
        attribute("sizes", sizes);
        return this;
    }

    public Source media(String media) {
        attribute("media", media);
        return this;
    }
}
