package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class HeaderCell extends Tag<HeaderCell> {

    public HeaderCell(Component... children) {
        super("th", children);
    }

    public HeaderCell scope(String scope) {
        attribute("scope", scope);
        return this;
    }

    public HeaderCell abbr(String abbr) {
        attribute("abbr", abbr);
        return this;
    }

    public HeaderCell colSpan(int colSpan) {
        attribute("colspan", String.valueOf(colSpan));
        return this;
    }

    public HeaderCell rowSpan(int rowSpan) {
        attribute("rowspan", String.valueOf(rowSpan));
        return this;
    }
}
