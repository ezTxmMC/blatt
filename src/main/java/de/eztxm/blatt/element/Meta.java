package de.eztxm.blatt.element;

import de.eztxm.blatt.core.VoidTag;

public final class Meta extends VoidTag<Meta> {

    public Meta() {
        super("meta");
    }

    public Meta name(String name) {
        attribute("name", name);
        return this;
    }

    public Meta content(String content) {
        attribute("content", content);
        return this;
    }

    public Meta charset(String charset) {
        attribute("charset", charset);
        return this;
    }

    public Meta property(String property) {
        attribute("property", property);
        return this;
    }

    public Meta httpEquiv(String httpEquiv) {
        attribute("http-equiv", httpEquiv);
        return this;
    }
}
