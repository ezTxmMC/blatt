package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Dialog extends Tag<Dialog> {

    public Dialog(Component... children) {
        super("dialog", children);
    }

    public Dialog open() {
        attribute("open", "");
        return this;
    }
}
