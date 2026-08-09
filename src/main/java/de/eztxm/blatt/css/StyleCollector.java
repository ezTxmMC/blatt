package de.eztxm.blatt.css;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

public final class StyleCollector {

    private final Set<String> keys;
    private final Map<String, String> classes;
    private final StyleSheet collected;

    public StyleCollector() {
        keys = new HashSet<>();
        classes = new HashMap<>();
        collected = new StyleSheet();
    }

    public void add(String key, Supplier<StyleSheet> styles) {
        if (keys.add(key)) {
            collected.with(styles.get());
        }
    }

    public String register(String signature, Function<String, StyleSheet> styles) {
        String existing = classes.get(signature);

        if (existing != null) {
            return existing;
        }

        String className = "b-" + (classes.size() + 1);
        classes.put(signature, className);
        collected.with(styles.apply(className));

        return className;
    }

    public boolean isEmpty() {
        return collected.isEmpty();
    }

    public StyleSheet sheet() {
        return collected;
    }
}
