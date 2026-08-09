package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class ColumnGroup extends Tag<ColumnGroup> {

    public ColumnGroup(Component... children) {
        super("colgroup", children);
    }

    public ColumnGroup span(int span) {
        attribute("span", String.valueOf(span));
        return this;
    }
}
