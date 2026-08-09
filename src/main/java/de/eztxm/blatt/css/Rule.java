package de.eztxm.blatt.css;

import java.util.ArrayList;
import java.util.List;

public final class Rule implements CssRule {

    private final String selector;
    private final Style style;
    private final List<Rule> nested;

    public Rule(String selector) {
        this(selector, new Style());
    }

    public Rule(String selector, Style style) {
        this.selector = selector;
        this.style = style;
        this.nested = new ArrayList<>();
    }

    public Rule set(String property, String value) {
        style.set(property, value);
        return this;
    }

    public Rule nested(String selector, Style style) {
        return nested(new Rule(selector, style));
    }

    public Rule nested(Rule rule) {
        nested.add(rule);
        return this;
    }

    public Rule on(String state, Style style) {
        return nested("&" + state, style);
    }

    public Style style() {
        return style;
    }

    @Override
    public void writeTo(CssWriter writer, CssContext context) {
        String resolved = resolve(context.parent(), selector);
        String written = context.selector(resolved);

        if (!style.isEmpty() && !written.isEmpty()) {
            writer.openBlock(written);
            style.writeTo(writer);
            writer.closeBlock();
        }

        for (Rule child : nested) {
            child.writeTo(writer, context.nested(resolved));
        }
    }

    private static String resolve(String parent, String selector) {
        if (parent.isEmpty()) {
            return selector.replace("&", "").trim();
        }

        if (selector.contains("&")) {
            return selector.replace("&", parent);
        }

        return parent + " " + selector;
    }
}
