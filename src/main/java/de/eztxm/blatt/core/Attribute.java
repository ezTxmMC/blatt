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

    public boolean hasToken(String token) {
        for (String part : value.split(" ")) {
            if (part.equals(token)) {
                return true;
            }
        }

        return false;
    }

    public String value() {
        return value;
    }

    public void writeTo(HtmlWriter writer) {
        writer.raw(" " + name + "=\"" + HtmlEscape.attribute(value) + "\"");
    }
}
