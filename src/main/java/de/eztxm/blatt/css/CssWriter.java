package de.eztxm.blatt.css;

public final class CssWriter {

    private final StringBuilder builder;

    public CssWriter() {
        builder = new StringBuilder();
    }

    public void openBlock(String selector) {
        builder.append(CssEscape.selector(selector)).append('{');
    }

    public void closeBlock() {
        builder.append('}');
    }

    public void declaration(String property, String value) {
        builder.append(CssEscape.property(property))
            .append(':')
            .append(CssEscape.value(value))
            .append(';');
    }

    public void raw(String value) {
        builder.append(value);
    }

    public String result() {
        return builder.toString();
    }
}
