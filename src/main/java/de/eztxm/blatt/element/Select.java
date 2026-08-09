package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Select extends Tag<Select> {

    public Select(Component... children) {
        super("select", children);
    }

    public Select name(String name) {
        attribute("name", name);
        return this;
    }

    public Select form(String form) {
        attribute("form", form);
        return this;
    }

    public Select size(int size) {
        attribute("size", String.valueOf(size));
        return this;
    }

    public Select multiple() {
        attribute("multiple", "");
        return this;
    }

    public Select disabled() {
        attribute("disabled", "");
        return this;
    }

    public Select required() {
        attribute("required", "");
        return this;
    }
}
