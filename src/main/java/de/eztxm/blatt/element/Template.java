package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Template extends Tag<Template> {

    public Template(Component... children) {
        super("template", children);
    }
}
