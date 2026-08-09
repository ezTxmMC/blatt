package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.EventHandler;
import de.eztxm.blatt.core.Tag;

import java.util.Optional;

public final class TextArea extends Tag<TextArea> {

    public TextArea(Component... children) {
        super("textarea", children);
    }

    public TextArea name(String name) {
        attribute("name", name);
        return this;
    }

    public TextArea placeholder(String placeholder) {
        attribute("placeholder", placeholder);
        return this;
    }

    public TextArea wrap(String wrap) {
        attribute("wrap", wrap);
        return this;
    }

    public TextArea form(String form) {
        attribute("form", form);
        return this;
    }

    public TextArea rows(int rows) {
        attribute("rows", String.valueOf(rows));
        return this;
    }

    public TextArea cols(int cols) {
        attribute("cols", String.valueOf(cols));
        return this;
    }

    public TextArea maxLength(int maxLength) {
        attribute("maxlength", String.valueOf(maxLength));
        return this;
    }

    public TextArea required() {
        attribute("required", "");
        return this;
    }

    public TextArea disabled() {
        attribute("disabled", "");
        return this;
    }

    public TextArea readOnly() {
        attribute("readonly", "");
        return this;
    }

    public TextArea onInput(String javascript) {
        attribute("oninput", javascript);
        return this;
    }

    public TextArea onInput(EventHandler handler) {
        attribute("oninput", handler.toJs(Optional.of("event")));
        return this;
    }

    public TextArea onChange(String javascript) {
        attribute("onchange", javascript);
        return this;
    }

    public TextArea onChange(EventHandler handler) {
        attribute("onchange", handler.toJs(Optional.of("event")));
        return this;
    }
}
