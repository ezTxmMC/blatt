package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class DescriptionTerm extends Tag<DescriptionTerm> {

    public DescriptionTerm(Component... children) {
        super("dt", children);
    }
}
