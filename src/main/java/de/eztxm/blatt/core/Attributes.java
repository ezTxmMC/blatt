package de.eztxm.blatt.core;

import java.util.ArrayList;
import java.util.List;

public final class Attributes {

    private final List<Attribute> values;

    public Attributes() {
        values = new ArrayList<>();
    }

    public void set(String name, String value) {
        remove(name);
        values.add(new Attribute(name, value));
    }

    public void writeTo(HtmlWriter writer) {
        for (Attribute attribute : values) {
            attribute.writeTo(writer);
        }
    }

    private void remove(String name) {
        values.removeIf(attribute -> attribute.hasName(name));
    }
}
