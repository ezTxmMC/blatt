package de.eztxm.blatt.core;

public final class HtmlEscape {

    private HtmlEscape() {}

    public static String content(String value) {
        return value
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;");
    }

    public static String attribute(String value) {
        return content(value)
            .replace("\"", "&quot;");
    }
}
