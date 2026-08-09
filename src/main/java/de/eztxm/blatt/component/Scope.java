package de.eztxm.blatt.component;

import de.eztxm.blatt.core.Markup;
import de.eztxm.blatt.css.StyleSheet;

public final class Scope {

    private final String name;
    private final boolean scoped;

    private Scope(String name, boolean scoped) {
        this.name = name;
        this.scoped = scoped;
    }

    public static Scope of(Class<?> type) {
        Component annotation = type.getAnnotation(Component.class);

        if (annotation == null) {
            return new Scope(token(type, ""), true);
        }

        return new Scope(token(type, annotation.value()), annotation.scoped());
    }

    public boolean isScoped() {
        return scoped;
    }

    public String name() {
        return name;
    }

    public String attribute() {
        return "data-" + name;
    }

    public String selector() {
        return "[" + attribute() + "]";
    }

    public void mark(Markup root) {
        if (scoped) {
            walk(root);
        }
    }

    private void walk(Markup markup) {
        markup.attr(attribute(), "");

        for (var child : markup.children()) {
            if (child instanceof Markup nested) {
                walk(nested);
            } else if (child instanceof Widget widget) {
                widget.inherit(attribute());
            }
        }
    }

    public StyleSheet apply(StyleSheet styles) {
        return scoped ? styles.scoped(selector()) : styles;
    }

    private static String token(Class<?> type, String configured) {
        String base = configured.isEmpty() ? kebab(type.getSimpleName()) : sanitize(configured);
        String hash = Integer.toUnsignedString(type.getName().hashCode(), 36);

        return base.isEmpty() ? "blatt-" + hash : base + "-" + hash;
    }

    private static String kebab(String value) {
        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < value.length(); index++) {
            char character = value.charAt(index);

            if (Character.isUpperCase(character) && index > 0) {
                builder.append('-');
            }

            builder.append(Character.toLowerCase(character));
        }

        return sanitize(builder.toString());
    }

    private static String sanitize(String value) {
        StringBuilder builder = new StringBuilder();

        for (char character : value.toLowerCase().toCharArray()) {
            if (Character.isLetterOrDigit(character) || character == '-') {
                builder.append(character);
            }
        }

        return builder.toString();
    }
}
