package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class IFrame extends Tag<IFrame> {

    public IFrame(Component... children) {
        super("iframe", children);
    }

    public IFrame src(String src) {
        attribute("src", src);
        return this;
    }

    public IFrame name(String name) {
        attribute("name", name);
        return this;
    }

    public IFrame loading(String loading) {
        attribute("loading", loading);
        return this;
    }

    public IFrame allow(String allow) {
        attribute("allow", allow);
        return this;
    }

    public IFrame width(int width) {
        attribute("width", String.valueOf(width));
        return this;
    }

    public IFrame height(int height) {
        attribute("height", String.valueOf(height));
        return this;
    }
}
