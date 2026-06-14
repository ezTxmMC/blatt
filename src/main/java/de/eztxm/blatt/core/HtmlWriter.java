package de.eztxm.blatt.core;

public final class HtmlWriter {

    private final StringBuilder builder;

    public HtmlWriter() {
        builder = new StringBuilder();
    }

    public void openTag(String tag, Attributes attributes) {
        builder.append('<').append(tag);
        attributes.writeTo(this);
        builder.append('>');
    }

    public void closeTag(String tag) {
        builder.append("</").append(tag).append('>');
    }

    public void text(String value) {
        builder.append(HtmlEscape.content(value));
    }

    public void raw(String value) {
        builder.append(value);
    }

    public String result() {
        return builder.toString();
    }
}
