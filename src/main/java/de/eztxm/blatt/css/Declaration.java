package de.eztxm.blatt.css;

public final class Declaration {

    private final String property;
    private final String value;

    public Declaration(String property, String value) {
        this.property = property;
        this.value = value;
    }

    public boolean hasProperty(String other) {
        return property.equals(other);
    }

    public String property() {
        return property;
    }

    public String value() {
        return value;
    }

    public void writeTo(CssWriter writer) {
        writer.declaration(property, value);
    }
}
