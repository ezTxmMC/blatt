package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class TableHead extends Tag<TableHead> {

    public TableHead(Component... children) {
        super("thead", children);
    }
}
