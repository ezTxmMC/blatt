package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Address extends Tag<Address> {

    public Address(Component... children) {
        super("address", children);
    }
}
