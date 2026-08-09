package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Script extends Tag<Script> {

    public Script(Component... children) {
        super("script", children);
    }

    public Script src(String src) {
        attribute("src", src);
        return this;
    }

    public Script type(String type) {
        attribute("type", type);
        return this;
    }

    public Script defer() {
        attribute("defer", "");
        return this;
    }

    public Script async() {
        attribute("async", "");
        return this;
    }

    public Script noModule() {
        attribute("nomodule", "");
        return this;
    }
}
