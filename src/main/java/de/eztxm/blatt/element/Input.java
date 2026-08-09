package de.eztxm.blatt.element;

import de.eztxm.blatt.core.EventHandler;
import de.eztxm.blatt.core.VoidTag;

import java.util.Optional;

public final class Input extends VoidTag<Input> {

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

    public Input min(String min) {
        attribute("min", min);
        return this;
    }

    public Input max(String max) {
        attribute("max", max);
        return this;
    }

    public Input step(String step) {
        attribute("step", step);
        return this;
    }

    public Input pattern(String pattern) {
        attribute("pattern", pattern);
        return this;
    }

    public Input autocomplete(String autocomplete) {
        attribute("autocomplete", autocomplete);
        return this;
    }

    public Input accept(String accept) {
        attribute("accept", accept);
        return this;
    }

    public Input form(String form) {
        attribute("form", form);
        return this;
    }

    public Input list(String list) {
        attribute("list", list);
        return this;
    }

    public Input maxLength(int maxLength) {
        attribute("maxlength", String.valueOf(maxLength));
        return this;
    }

    public Input required() {
        attribute("required", "");
        return this;
    }

    public Input disabled() {
        attribute("disabled", "");
        return this;
    }

    public Input readOnly() {
        attribute("readonly", "");
        return this;
    }

    public Input checked() {
        attribute("checked", "");
        return this;
    }

    public Input multiple() {
        attribute("multiple", "");
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
