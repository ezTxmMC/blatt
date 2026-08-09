package de.eztxm.blatt.component;

import de.eztxm.blatt.core.Element;
import de.eztxm.blatt.core.HtmlWriter;
import de.eztxm.blatt.css.StyleSheet;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Widget implements de.eztxm.blatt.core.Component {

    private final Scope scope;
    private final Set<String> inherited;

    protected Widget() {
        scope = Scope.of(getClass());
        inherited = new LinkedHashSet<>();
    }

    protected abstract Element root();

    protected StyleSheet styles() {
        return new StyleSheet();
    }

    public Scope scope() {
        return scope;
    }

    public StyleSheet scopedStyles() {
        return scope.apply(styles());
    }

    @Override
    public final void render(HtmlWriter writer) {
        writer.styles().add(scope.name(), this::scopedStyles);

        Element element = root();
        scope.mark(element);

        for (String attribute : inherited) {
            element.attr(attribute, "");
        }

        element.render(writer);
    }

    void inherit(String attribute) {
        inherited.add(attribute);
    }
}
