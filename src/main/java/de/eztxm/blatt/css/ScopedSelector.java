package de.eztxm.blatt.css;

import java.util.ArrayList;
import java.util.List;

public final class ScopedSelector {

    private ScopedSelector() {}

    public static String apply(String selector, String scope) {
        if (scope.isEmpty()) {
            return selector;
        }

        if (selector.isBlank()) {
            return scope;
        }

        StringBuilder result = new StringBuilder();

        for (String part : split(selector)) {
            if (result.length() > 0) {
                result.append(",");
            }

            result.append(insert(part.trim(), scope));
        }

        return result.toString();
    }

    private static String insert(String selector, String scope) {
        if (selector.isEmpty()) {
            return scope;
        }

        boolean[] topLevel = topLevel(selector);
        int compound = compoundStart(selector, topLevel);
        int pseudo = pseudoStart(selector, topLevel, compound);

        return selector.substring(0, pseudo) + scope + selector.substring(pseudo);
    }

    private static int compoundStart(String selector, boolean[] topLevel) {
        for (int index = selector.length() - 1; index >= 0; index--) {
            if (topLevel[index] && isCombinator(selector.charAt(index))) {
                return index + 1;
            }
        }

        return 0;
    }

    private static int pseudoStart(String selector, boolean[] topLevel, int from) {
        for (int index = from; index < selector.length(); index++) {
            if (topLevel[index] && selector.charAt(index) == ':') {
                return index;
            }
        }

        return selector.length();
    }

    private static List<String> split(String selector) {
        boolean[] topLevel = topLevel(selector);
        List<String> parts = new ArrayList<>();
        int start = 0;

        for (int index = 0; index < selector.length(); index++) {
            if (topLevel[index] && selector.charAt(index) == ',') {
                parts.add(selector.substring(start, index));
                start = index + 1;
            }
        }

        parts.add(selector.substring(start));
        return parts;
    }

    private static boolean[] topLevel(String selector) {
        boolean[] flags = new boolean[selector.length()];
        int depth = 0;
        char quote = 0;

        for (int index = 0; index < selector.length(); index++) {
            char character = selector.charAt(index);

            if (quote != 0) {
                if (character == quote) {
                    quote = 0;
                }
                continue;
            }

            if (character == '"' || character == '\'') {
                quote = character;
                continue;
            }

            if (character == '(' || character == '[') {
                depth++;
                continue;
            }

            if (character == ')' || character == ']') {
                depth--;
                continue;
            }

            flags[index] = depth == 0;
        }

        return flags;
    }

    private static boolean isCombinator(char character) {
        return character == ' ' || character == '>' || character == '+' || character == '~';
    }
}
