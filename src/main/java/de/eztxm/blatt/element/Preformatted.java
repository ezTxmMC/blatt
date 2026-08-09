package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Preformatted extends Tag<Preformatted> {

    public Preformatted(Component... children) {
        super("pre", children);
    }
}
