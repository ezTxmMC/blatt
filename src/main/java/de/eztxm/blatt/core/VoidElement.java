package de.eztxm.blatt.core;

public abstract class VoidElement implements Component {

    private final String tag;
    private final Attributes attributes;

    protected VoidElement(String tag) {
        this.tag = tag;
        this.attributes = new Attributes();
    }

    protected void attribute(String name, String value) {
        attributes.set(name, value);
    }

    public VoidElement id(String id) {
        attribute("id", id);
        return this;
    }

    public VoidElement cssClass(String cssClass) {
        attribute("class", cssClass);
        return this;
    }

    public VoidElement attr(String name, String value) {
        attribute(name, value);
        return this;
    }

    @Override
    public void render(HtmlWriter writer) {
        writer.openTag(tag, attributes);
    }
}
