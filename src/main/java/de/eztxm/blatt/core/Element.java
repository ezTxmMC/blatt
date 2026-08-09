package de.eztxm.blatt.core;

import de.eztxm.blatt.css.Style;
import de.eztxm.blatt.css.StyleRules;

import java.util.ArrayList;
import java.util.List;

public abstract class Element implements Markup {

    private final String tag;
    private final Attributes attributes;
    private final List<Component> children;
    private final StyleRules rules;

    protected Element(String tag, Component... children) {
        this.tag = tag;
        this.attributes = new Attributes();
        this.children = new ArrayList<>(List.of(children));
        this.rules = new StyleRules();
    }

    protected void attribute(String name, String value) {
        attributes.set(name, value);
    }

    protected void append(Component... more) {
        children.addAll(List.of(more));
    }

    protected void rule(String state, Style style) {
        rules.add(state, style);
    }

    @Override
    public Element attr(String name, String value) {
        attribute(name, value);
        return this;
    }

    @Override
    public List<Component> children() {
        return List.copyOf(children);
    }

    @Override
    public void render(HtmlWriter writer) {
        applyRules(writer);
        writer.openTag(tag, attributes);
        renderChildren(writer);
        writer.closeTag(tag);
    }

    private void applyRules(HtmlWriter writer) {
        if (rules.isEmpty()) {
            return;
        }

        attributes.add("class", writer.styles().register(rules.signature(), rules::sheet));
    }

    private void renderChildren(HtmlWriter writer) {
        for (Component child : children) {
            child.render(writer);
        }
    }
}
