package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Audio extends Tag<Audio> {

    public Audio(Component... children) {
        super("audio", children);
    }

    public Audio src(String src) {
        attribute("src", src);
        return this;
    }

    public Audio preload(String preload) {
        attribute("preload", preload);
        return this;
    }

    public Audio controls() {
        attribute("controls", "");
        return this;
    }

    public Audio autoplay() {
        attribute("autoplay", "");
        return this;
    }

    public Audio loop() {
        attribute("loop", "");
        return this;
    }

    public Audio muted() {
        attribute("muted", "");
        return this;
    }
}
