package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Variable extends Tag<Variable> {

    public Variable(Component... children) {
        super("var", children);
    }
}
