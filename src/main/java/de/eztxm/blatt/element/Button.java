package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Element;
import de.eztxm.blatt.core.EventHandler;

import java.util.Optional;

public final class Button extends Element {

    public Button(Component... children) {
        super("button", children);
    }

    public Button onClick(String javascript) {
        attribute("onclick", javascript);
        return this;
    }

    public Button onClick(EventHandler handler) {
        attribute("onclick", handler.toJs(Optional.of("event")));
        return this;
    }

    public Button type(String type) {
        attribute("type", type);
        return this;
    }
}
