package de.eztxm.blatt.core;

import de.eztxm.blatt.css.CssWriter;
import de.eztxm.blatt.css.StyleSheet;

import java.util.ArrayList;
import java.util.List;

public final class HeadConfig {

    private String title;
    private String lang;
    private final List<String> scriptSrcs;
    private final List<String> stylesheetHrefs;
    private final List<StyleSheet> styles;
    private final List<String> tailwindCss;
    private boolean tailwind;

    public HeadConfig() {
        title = "";
        lang = "de";
        scriptSrcs = new ArrayList<>();
        stylesheetHrefs = new ArrayList<>();
        styles = new ArrayList<>();
        tailwindCss = new ArrayList<>();
    }

    public HeadConfig title(String title) {
        this.title = title;
        return this;
    }

    public HeadConfig lang(String lang) {
        this.lang = lang;
        return this;
    }

    public HeadConfig script(String src) {
        scriptSrcs.add(src);
        return this;
    }

    public HeadConfig stylesheet(String href) {
        stylesheetHrefs.add(href);
        return this;
    }

    public HeadConfig style(StyleSheet style) {
        styles.add(style);
        return this;
    }

    /** Enables experimental Tailwind CSS (v4 browser build) for this document. */
    public HeadConfig tailwind() {
        tailwind = true;
        return this;
    }

    /** Enables experimental Tailwind CSS and adds custom CSS such as {@code @theme} blocks. */
    public HeadConfig tailwind(String css) {
        tailwind = true;
        tailwindCss.add(css);
        return this;
    }

    public String title() {
        return title;
    }

    public String lang() {
        return lang;
    }

    public List<String> scriptSrcs() {
        return List.copyOf(scriptSrcs);
    }

    public List<String> stylesheetHrefs() {
        return List.copyOf(stylesheetHrefs);
    }

    public List<StyleSheet> styles() {
        return List.copyOf(styles);
    }

    public boolean usesTailwind() {
        return tailwind;
    }

    public List<String> tailwindCss() {
        return List.copyOf(tailwindCss);
    }

    public String renderStyles() {
        CssWriter writer = new CssWriter();
        for (StyleSheet style : styles) {
            style.writeTo(writer);
        }

        return writer.result();
    }
}
