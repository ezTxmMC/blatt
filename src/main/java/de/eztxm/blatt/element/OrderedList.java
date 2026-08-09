package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class OrderedList extends Tag<OrderedList> {

    public OrderedList(Component... children) {
        super("ol", children);
    }

    public OrderedList start(int start) {
        attribute("start", String.valueOf(start));
        return this;
    }

    public OrderedList type(String type) {
        attribute("type", type);
        return this;
    }

    public OrderedList reversed() {
        attribute("reversed", "");
        return this;
    }
}
