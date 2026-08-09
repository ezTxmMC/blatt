package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Menu extends Tag<Menu> {

    public Menu(Component... children) {
        super("menu", children);
    }
}
