package de.eztxm.blatt.element;

import de.eztxm.blatt.core.VoidTag;

public final class Track extends VoidTag<Track> {

    public Track() {
        super("track");
    }

    public Track src(String src) {
        attribute("src", src);
        return this;
    }

    public Track kind(String kind) {
        attribute("kind", kind);
        return this;
    }

    public Track label(String label) {
        attribute("label", label);
        return this;
    }

    public Track srcLang(String srcLang) {
        attribute("srclang", srcLang);
        return this;
    }
}
