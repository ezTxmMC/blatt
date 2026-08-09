package de.eztxm.blatt.element;

import de.eztxm.blatt.core.VoidTag;

public final class Link extends VoidTag<Link> {

    public Link() {
        super("link");
    }

    public Link rel(String rel) {
        attribute("rel", rel);
        return this;
    }

    public Link href(String href) {
        attribute("href", href);
        return this;
    }

    public Link type(String type) {
        attribute("type", type);
        return this;
    }

    public Link as(String as) {
        attribute("as", as);
        return this;
    }

    public Link media(String media) {
        attribute("media", media);
        return this;
    }

    public Link crossOrigin(String crossOrigin) {
        attribute("crossorigin", crossOrigin);
        return this;
    }
}
