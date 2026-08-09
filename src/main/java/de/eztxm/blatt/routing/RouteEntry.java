package de.eztxm.blatt.routing;

import de.eztxm.blatt.css.StyleSheet;

import java.util.ArrayList;
import java.util.List;

public final class RouteEntry {

    private final String path;
    private final Page page;
    private final List<String> scripts;
    private final List<String> stylesheets;
    private final List<StyleSheet> styles;

    public RouteEntry(String path, Page page) {
        this.path = path;
        this.page = page;
        this.scripts = new ArrayList<>();
        this.stylesheets = new ArrayList<>();
        this.styles = new ArrayList<>();
    }

    public RouteEntry script(String src) {
        scripts.add(src);
        return this;
    }

    public RouteEntry stylesheet(String href) {
        stylesheets.add(href);
        return this;
    }

    public RouteEntry style(StyleSheet style) {
        styles.add(style);
        return this;
    }

    public boolean matches(String requested) {
        return path.equals(requested);
    }

    public String path() {
        return path;
    }

    public Page page() {
        return page;
    }

    public List<String> scripts() {
        return List.copyOf(scripts);
    }

    public List<String> stylesheets() {
        return List.copyOf(stylesheets);
    }

    public List<StyleSheet> styles() {
        return List.copyOf(styles);
    }
}
