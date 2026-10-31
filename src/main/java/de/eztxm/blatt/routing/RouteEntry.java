package de.eztxm.blatt.routing;

import de.eztxm.blatt.css.StyleSheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class RouteEntry {

    private final String path;
    private final HttpMethod method;
    private final Page page;
    private final Endpoint endpoint;
    private final List<String> scripts;
    private final List<String> stylesheets;
    private final List<StyleSheet> styles;
    private final List<String> tailwindCss;
    private boolean tailwind;

    public RouteEntry(String path, Page page) {
        this(path, HttpMethod.GET, page, null);
    }

    public RouteEntry(String path, HttpMethod method, Page page) {
        this(path, method, page, null);
    }

    public RouteEntry(String path, HttpMethod method, Endpoint endpoint) {
        this(path, method, null, endpoint);
    }

    private RouteEntry(String path, HttpMethod method, Page page, Endpoint endpoint) {
        if (page == null && endpoint == null) {
            throw new IllegalArgumentException("A route needs a Page or an Endpoint: " + path);
        }
        this.path = path;
        this.method = method;
        this.page = page;
        this.endpoint = endpoint;
        this.scripts = new ArrayList<>();
        this.stylesheets = new ArrayList<>();
        this.styles = new ArrayList<>();
        this.tailwindCss = new ArrayList<>();
    }

    /** Applies to pages only; endpoints write their own response. */
    public RouteEntry script(String src) {
        scripts.add(src);
        return this;
    }

    /** Applies to pages only; endpoints write their own response. */
    public RouteEntry stylesheet(String href) {
        stylesheets.add(href);
        return this;
    }

    /** Applies to pages only; endpoints write their own response. */
    public RouteEntry style(StyleSheet style) {
        styles.add(style);
        return this;
    }

    /** Enables experimental Tailwind CSS for this page. Applies to pages only. */
    public RouteEntry tailwind() {
        tailwind = true;
        return this;
    }

    /** Enables experimental Tailwind CSS and adds custom CSS such as {@code @theme} blocks. */
    public RouteEntry tailwind(String css) {
        tailwind = true;
        tailwindCss.add(css);
        return this;
    }

    /** Exact match, or a match against {@code {name}} parameter segments. */
    public boolean matches(String requested) {
        return PathPattern.matches(path, requested);
    }

    public String path() {
        return path;
    }

    public HttpMethod method() {
        return method;
    }

    public Optional<Page> page() {
        return Optional.ofNullable(page);
    }

    public Optional<Endpoint> endpoint() {
        return Optional.ofNullable(endpoint);
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

    public boolean usesTailwind() {
        return tailwind;
    }

    public List<String> tailwindCss() {
        return List.copyOf(tailwindCss);
    }
}
