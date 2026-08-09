package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Title extends Tag<Title> {

    public Title(Component... children) {
        super("title", children);
    }
}
