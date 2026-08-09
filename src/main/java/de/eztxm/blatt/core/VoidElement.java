package de.eztxm.blatt.core;

import de.eztxm.blatt.css.Style;
import de.eztxm.blatt.css.StyleRules;

import java.util.List;

public abstract class VoidElement implements Markup {

    private final String tag;
    private final Attributes attributes;
    private final StyleRules rules;

    protected VoidElement(String tag) {
        this.tag = tag;
        this.attributes = new Attributes();
        this.rules = new StyleRules();
    }

    protected void attribute(String name, String value) {
        attributes.set(name, value);
    }

    protected void rule(String state, Style style) {
        rules.add(state, style);
    }

    @Override
    public VoidElement attr(String name, String value) {
        attribute(name, value);
        return this;
    }

    @Override
    public List<Component> children() {
        return List.of();
    }

    @Override
    public void render(HtmlWriter writer) {
        applyRules(writer);
        writer.openTag(tag, attributes);
    }

    private void applyRules(HtmlWriter writer) {
        if (rules.isEmpty()) {
            return;
        }

        attributes.add("class", writer.styles().register(rules.signature(), rules::sheet));
    }
}
