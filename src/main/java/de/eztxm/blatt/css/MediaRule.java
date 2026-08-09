package de.eztxm.blatt.css;

import java.util.ArrayList;
import java.util.List;

public final class MediaRule implements CssRule {

    private final String condition;
    private final List<CssRule> rules;

    public MediaRule(String condition) {
        this.condition = condition;
        this.rules = new ArrayList<>();
    }

    public static MediaRule minWidth(String width) {
        return new MediaRule("(min-width: " + width + ")");
    }

    public static MediaRule maxWidth(String width) {
        return new MediaRule("(max-width: " + width + ")");
    }

    public static MediaRule darkScheme() {
        return new MediaRule("(prefers-color-scheme: dark)");
    }

    public static MediaRule lightScheme() {
        return new MediaRule("(prefers-color-scheme: light)");
    }

    public static MediaRule reducedMotion() {
        return new MediaRule("(prefers-reduced-motion: reduce)");
    }

    public static MediaRule print() {
        return new MediaRule("print");
    }

    public MediaRule rule(String selector, Style style) {
        return rule(new Rule(selector, style));
    }

    public MediaRule rule(CssRule rule) {
        rules.add(rule);
        return this;
    }

    @Override
    public void writeTo(CssWriter writer, CssContext context) {
        if (rules.isEmpty()) {
            return;
        }

        writer.raw("@media " + CssEscape.selector(condition) + "{");
        for (CssRule rule : rules) {
            rule.writeTo(writer, context);
        }
        writer.raw("}");
    }
}
