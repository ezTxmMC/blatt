package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Time extends Tag<Time> {

    public Time(Component... children) {
        super("time", children);
    }

    public Time dateTime(String dateTime) {
        attribute("datetime", dateTime);
        return this;
    }
}
