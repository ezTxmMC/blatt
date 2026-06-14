package de.eztxm.blatt.routing;

import java.util.ArrayList;
import java.util.List;

public final class RouteEntry {

    private final String path;
    private final Page page;
    private final List<String> scripts;
    private final List<String> stylesheets;

    public RouteEntry(String path, Page page) {
        this.path = path;
        this.page = page;
        this.scripts = new ArrayList<>();
        this.stylesheets = new ArrayList<>();
    }

    public RouteEntry script(String src) {
        scripts.add(src);
        return this;
    }

    public RouteEntry stylesheet(String href) {
        stylesheets.add(href);
        return this;
    }

    public boolean matches(String requested) {
        return path.equals(requested);
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
}
