package de.eztxm.blatt.css;

import java.util.ArrayList;
import java.util.List;

public final class StyleSheet {

    private final List<CssRule> rules;

    public StyleSheet() {
        rules = new ArrayList<>();
    }

    public StyleSheet rule(String selector, Style style) {
        return add(new Rule(selector, style));
    }

    public StyleSheet rule(Rule rule) {
        return add(rule);
    }

    public StyleSheet media(MediaRule media) {
        return add(media);
    }

    public StyleSheet keyframes(Keyframes keyframes) {
        return add(keyframes);
    }

    public StyleSheet variables(Style style) {
        return rule(":root", style);
    }

    public StyleSheet raw(String css) {
        return add((writer, context) -> writer.raw(css));
    }

    public StyleSheet scoped(String selector) {
        return new StyleSheet().add((writer, context) -> writeTo(writer, context.scoped(selector)));
    }

    public StyleSheet add(CssRule rule) {
        rules.add(rule);
        return this;
    }

    public StyleSheet with(StyleSheet other) {
        rules.addAll(other.rules);
        return this;
    }

    public boolean isEmpty() {
        return rules.isEmpty();
    }

    public void writeTo(CssWriter writer) {
        writeTo(writer, CssContext.root());
    }

    public void writeTo(CssWriter writer, CssContext context) {
        for (CssRule rule : rules) {
            rule.writeTo(writer, context);
        }
    }

    public String render() {
        CssWriter writer = new CssWriter();
        writeTo(writer);
        return writer.result();
    }
}
