package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class FieldSet extends Tag<FieldSet> {

    public FieldSet(Component... children) {
        super("fieldset", children);
    }

    public FieldSet name(String name) {
        attribute("name", name);
        return this;
    }

    public FieldSet disabled() {
        attribute("disabled", "");
        return this;
    }
}
