package de.eztxm.blatt.element;

import de.eztxm.blatt.core.VoidTag;

public final class Column extends VoidTag<Column> {

    public Column() {
        super("col");
    }

    public Column span(int span) {
        attribute("span", String.valueOf(span));
        return this;
    }
}
