package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Video extends Tag<Video> {

    public Video(Component... children) {
        super("video", children);
    }

    public Video src(String src) {
        attribute("src", src);
        return this;
    }

    public Video poster(String poster) {
        attribute("poster", poster);
        return this;
    }

    public Video preload(String preload) {
        attribute("preload", preload);
        return this;
    }

    public Video width(int width) {
        attribute("width", String.valueOf(width));
        return this;
    }

    public Video height(int height) {
        attribute("height", String.valueOf(height));
        return this;
    }

    public Video controls() {
        attribute("controls", "");
        return this;
    }

    public Video autoplay() {
        attribute("autoplay", "");
        return this;
    }

    public Video loop() {
        attribute("loop", "");
        return this;
    }

    public Video muted() {
        attribute("muted", "");
        return this;
    }

    public Video playsInline() {
        attribute("playsinline", "");
        return this;
    }
}
