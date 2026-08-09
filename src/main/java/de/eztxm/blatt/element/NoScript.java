package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class NoScript extends Tag<NoScript> {

    public NoScript(Component... children) {
        super("noscript", children);
    }
}
