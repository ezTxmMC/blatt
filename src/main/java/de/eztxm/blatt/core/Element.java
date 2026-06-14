package de.eztxm.blatt.core;

import java.util.ArrayList;
import java.util.List;

public abstract class Element implements Component {

    private final String tag;
    private final Attributes attributes;
    private final List<Component> children;

    protected Element(String tag, Component... children) {
        this.tag = tag;
        this.attributes = new Attributes();
        this.children = new ArrayList<>(List.of(children));
    }

    protected void attribute(String name, String value) {
        attributes.set(name, value);
    }

    public Element add(Component... more) {
        children.addAll(List.of(more));
        return this;
    }

    public Element id(String id) {
        attribute("id", id);
        return this;
    }

    public Element cssClass(String cssClass) {
        attribute("class", cssClass);
        return this;
    }

    public Element attr(String name, String value) {
        attribute(name, value);
        return this;
    }

    @Override
    public void render(HtmlWriter writer) {
        writer.openTag(tag, attributes);
        renderChildren(writer);
        writer.closeTag(tag);
    }

    private void renderChildren(HtmlWriter writer) {
        for (Component child : children) {
            child.render(writer);
        }
    }
}
