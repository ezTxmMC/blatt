package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class UnorderedList extends Tag<UnorderedList> {

    public UnorderedList(Component... children) {
        super("ul", children);
    }
}
