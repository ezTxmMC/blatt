package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Table extends Tag<Table> {

    public Table(Component... children) {
        super("table", children);
    }
}
