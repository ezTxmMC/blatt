package de.eztxm.blatt.css;

import java.util.ArrayList;
import java.util.List;

public final class Keyframes implements CssRule {

    private final String name;
    private final List<Rule> stops;

    public Keyframes(String name) {
        this.name = name;
        this.stops = new ArrayList<>();
    }

    public Keyframes at(String offset, Style style) {
        stops.add(new Rule(offset, style));
        return this;
    }

    public Keyframes from(Style style) {
        return at("from", style);
    }

    public Keyframes to(Style style) {
        return at("to", style);
    }

    public String name() {
        return name;
    }

    @Override
    public void writeTo(CssWriter writer, CssContext context) {
        if (stops.isEmpty()) {
            return;
        }

        writer.raw("@keyframes " + CssEscape.property(name) + "{");
        for (Rule stop : stops) {
            stop.writeTo(writer, CssContext.root());
        }
        writer.raw("}");
    }
}
