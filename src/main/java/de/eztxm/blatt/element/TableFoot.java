package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class TableFoot extends Tag<TableFoot> {

    public TableFoot(Component... children) {
        super("tfoot", children);
    }
}
