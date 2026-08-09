package de.eztxm.blatt.css;

import java.util.ArrayList;
import java.util.List;

public final class StyleRules {

    private static final String PLACEHOLDER = "b-x";

    private final List<Rule> rules;

    public StyleRules() {
        rules = new ArrayList<>();
    }

    public void add(String state, Style style) {
        rules.add(new Rule("&" + state, style));
    }

    public boolean isEmpty() {
        return rules.isEmpty();
    }

    public String signature() {
        return sheet(PLACEHOLDER).render();
    }

    public StyleSheet sheet(String className) {
        StyleSheet sheet = new StyleSheet();
        String parent = "." + className;

        for (Rule rule : rules) {
            sheet.add((writer, context) -> rule.writeTo(writer, context.nested(parent)));
        }

        return sheet;
    }
}
