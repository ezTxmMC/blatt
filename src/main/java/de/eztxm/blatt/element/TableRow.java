package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class TableRow extends Tag<TableRow> {

    public TableRow(Component... children) {
        super("tr", children);
    }
}
