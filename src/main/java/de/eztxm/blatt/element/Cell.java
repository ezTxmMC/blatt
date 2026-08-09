package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Cell extends Tag<Cell> {

    public Cell(Component... children) {
        super("td", children);
    }

    public Cell colSpan(int colSpan) {
        attribute("colspan", String.valueOf(colSpan));
        return this;
    }

    public Cell rowSpan(int rowSpan) {
        attribute("rowspan", String.valueOf(rowSpan));
        return this;
    }

    public Cell headers(String headers) {
        attribute("headers", headers);
        return this;
    }
}
