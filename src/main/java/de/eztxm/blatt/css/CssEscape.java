package de.eztxm.blatt.css;

public final class CssEscape {

    private CssEscape() {}

    public static String value(String value) {
        return value
            .replace("<", "\\3c ")
            .replace(">", "\\3e ");
    }

    public static String selector(String selector) {
        return selector.replace("<", "\\3c ");
    }

    public static String property(String property) {
        StringBuilder builder = new StringBuilder();
        for (char character : property.toCharArray()) {
            if (Character.isLetterOrDigit(character) || character == '-' || character == '_') {
                builder.append(character);
            }
        }

        return builder.toString();
    }
}
