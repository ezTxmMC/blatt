package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class DescriptionList extends Tag<DescriptionList> {

    public DescriptionList(Component... children) {
        super("dl", children);
    }
}
