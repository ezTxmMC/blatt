package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Abbreviation extends Tag<Abbreviation> {

    public Abbreviation(Component... children) {
        super("abbr", children);
    }
}
