package de.eztxm.blatt.element;

import de.eztxm.blatt.core.EventHandler;
import de.eztxm.blatt.core.VoidElement;

import java.util.Optional;

public final class Input extends VoidElement {

    public Input(String type) {
        super("input");
        attribute("type", type);
    }

    public Input name(String name) {
        attribute("name", name);
        return this;
    }

    public Input value(String value) {
        attribute("value", value);
        return this;
    }

    public Input placeholder(String placeholder) {
        attribute("placeholder", placeholder);
        return this;
    }

    public Input onInput(String javascript) {
        attribute("oninput", javascript);
        return this;
    }

    public Input onInput(EventHandler handler) {
        attribute("oninput", handler.toJs(Optional.of("event")));
        return this;
    }

    public Input onChange(String javascript) {
        attribute("onchange", javascript);
        return this;
    }

    public Input onChange(EventHandler handler) {
        attribute("onchange", handler.toJs(Optional.of("event")));
        return this;
    }
}
