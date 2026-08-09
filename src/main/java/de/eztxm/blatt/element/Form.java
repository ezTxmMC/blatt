package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Form extends Tag<Form> {

    public Form(Component... children) {
        super("form", children);
    }

    public Form action(String action) {
        attribute("action", action);
        return this;
    }

    public Form method(String method) {
        attribute("method", method);
        return this;
    }

    public Form name(String name) {
        attribute("name", name);
        return this;
    }

    public Form target(String target) {
        attribute("target", target);
        return this;
    }

    public Form enctype(String enctype) {
        attribute("enctype", enctype);
        return this;
    }

    public Form autocomplete(String autocomplete) {
        attribute("autocomplete", autocomplete);
        return this;
    }

    public Form noValidate() {
        attribute("novalidate", "");
        return this;
    }
}
