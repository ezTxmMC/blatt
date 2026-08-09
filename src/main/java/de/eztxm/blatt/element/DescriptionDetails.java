package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class DescriptionDetails extends Tag<DescriptionDetails> {

    public DescriptionDetails(Component... children) {
        super("dd", children);
    }
}
