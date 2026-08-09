package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class ListItem extends Tag<ListItem> {

    public ListItem(Component... children) {
        super("li", children);
    }

    public ListItem value(int value) {
        attribute("value", String.valueOf(value));
        return this;
    }
}
