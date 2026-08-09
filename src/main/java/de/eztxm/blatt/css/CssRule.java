package de.eztxm.blatt.css;

@FunctionalInterface
public interface CssRule {

    void writeTo(CssWriter writer, CssContext context);
}
