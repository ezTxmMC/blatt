package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class TableBody extends Tag<TableBody> {

    public TableBody(Component... children) {
        super("tbody", children);
    }
}
