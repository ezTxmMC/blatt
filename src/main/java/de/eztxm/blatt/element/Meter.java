package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Meter extends Tag<Meter> {

    public Meter(Component... children) {
        super("meter", children);
    }

    public Meter value(String value) {
        attribute("value", value);
        return this;
    }

    public Meter min(String min) {
        attribute("min", min);
        return this;
    }

    public Meter max(String max) {
        attribute("max", max);
        return this;
    }

    public Meter low(String low) {
        attribute("low", low);
        return this;
    }

    public Meter high(String high) {
        attribute("high", high);
        return this;
    }

    public Meter optimum(String optimum) {
        attribute("optimum", optimum);
        return this;
    }

    public Meter form(String form) {
        attribute("form", form);
        return this;
    }
}
