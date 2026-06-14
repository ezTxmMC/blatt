package de.eztxm.blatt.core;

public final class Attribute {

    private final String name;
    private final String value;

    public Attribute(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public boolean hasName(String other) {
        return name.equals(other);
    }

    public void writeTo(HtmlWriter writer) {
        writer.raw(" " + name + "=\"" + HtmlEscape.attribute(value) + "\"");
    }
}
