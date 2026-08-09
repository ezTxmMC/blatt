package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Sample extends Tag<Sample> {

    public Sample(Component... children) {
        super("samp", children);
    }
}
