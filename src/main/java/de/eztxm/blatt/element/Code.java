package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Code extends Tag<Code> {

    public Code(Component... children) {
        super("code", children);
    }
}
