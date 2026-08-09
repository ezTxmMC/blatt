package de.eztxm.blatt.core;

import de.eztxm.blatt.css.Style;

import java.util.Optional;

public abstract class Tag<S extends Tag<S>> extends Element {

    protected Tag(String tag, Component... children) {
        super(tag, children);
    }

    public S add(Component... more) {
        append(more);
        return self();
    }

    public S id(String id) {
        attribute("id", id);
        return self();
    }

    public S cssClass(String cssClass) {
        attribute("class", cssClass);
        return self();
    }

    public S style(Style style) {
        attribute("style", style.inline());
        return self();
    }

    public S on(String state, Style style) {
        rule(state, style);
        return self();
    }

    public S title(String title) {
        attribute("title", title);
        return self();
    }

    public S role(String role) {
        attribute("role", role);
        return self();
    }

    public S data(String name, String value) {
        attribute("data-" + name, value);
        return self();
    }

    public S aria(String name, String value) {
        attribute("aria-" + name, value);
        return self();
    }

    public S tabIndex(int tabIndex) {
        attribute("tabindex", String.valueOf(tabIndex));
        return self();
    }

    public S hidden() {
        attribute("hidden", "");
        return self();
    }

    public S onClick(String javascript) {
        attribute("onclick", javascript);
        return self();
    }

    public S onClick(EventHandler handler) {
        attribute("onclick", handler.toJs(Optional.of("event")));
        return self();
    }

    @Override
    public S attr(String name, String value) {
        attribute(name, value);
        return self();
    }

    @SuppressWarnings("unchecked")
    private S self() {
        return (S) this;
    }
}
