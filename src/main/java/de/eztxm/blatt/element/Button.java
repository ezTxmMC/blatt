package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Button extends Tag<Button> {

    public Button(Component... children) {
        super("button", children);
    }

    public Button type(String type) {
        attribute("type", type);
        return this;
    }

    public Button name(String name) {
        attribute("name", name);
        return this;
    }

    public Button value(String value) {
        attribute("value", value);
        return this;
    }

    public Button form(String form) {
        attribute("form", form);
        return this;
    }

    public Button disabled() {
        attribute("disabled", "");
        return this;
    }
}
