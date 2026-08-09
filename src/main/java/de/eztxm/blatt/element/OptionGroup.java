package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class OptionGroup extends Tag<OptionGroup> {

    public OptionGroup(Component... children) {
        super("optgroup", children);
    }

    public OptionGroup label(String label) {
        attribute("label", label);
        return this;
    }

    public OptionGroup disabled() {
        attribute("disabled", "");
        return this;
    }
}
