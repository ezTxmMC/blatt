package de.eztxm.blatt.routing;

import de.eztxm.blatt.css.StyleSheet;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class Router {

    private final List<RouteEntry> routes;
    private final Map<HttpMethod, Map<String, RouteEntry>> index;
    private final Map<HttpMethod, List<RouteEntry>> parameterized;
    private final List<String> globalScripts;
    private final List<String> globalStylesheets;
    private final List<StyleSheet> globalStyles;
    private final List<String> globalTailwindCss;
    private Optional<Path> staticDir;
    private boolean tailwind;

    public Router() {
        routes = new ArrayList<>();
        index = new EnumMap<>(HttpMethod.class);
        parameterized = new EnumMap<>(HttpMethod.class);
        for (HttpMethod method : HttpMethod.values()) {
            index.put(method, new HashMap<>());
            parameterized.put(method, new ArrayList<>());
        }
        globalScripts = new ArrayList<>();
        globalStylesheets = new ArrayList<>();
        globalStyles = new ArrayList<>();
        globalTailwindCss = new ArrayList<>();
        staticDir = Optional.empty();
    }

    public Router staticFiles(Path dir) {
        staticDir = Optional.of(dir.toAbsolutePath());
        return this;
    }

    public Router script(String src) {
        globalScripts.add(src);
        return this;
    }

    public Router stylesheet(String href) {
        globalStylesheets.add(href);
        return this;
    }

    public Router style(StyleSheet style) {
        globalStyles.add(style);
        return this;
    }

    /** Enables experimental Tailwind CSS for every page. */
    public Router tailwind() {
        tailwind = true;
        return this;
    }

    /** Enables experimental Tailwind CSS for every page and adds custom CSS such as {@code @theme} blocks. */
    public Router tailwind(String css) {
        tailwind = true;
        globalTailwindCss.add(css);
        return this;
    }

    public RouteEntry register(String path, Page page) {
        return register(HttpMethod.GET, path, page);
    }

    public RouteEntry register(HttpMethod method, String path, Page page) {
        return add(new RouteEntry(path, method, page));
    }

    public RouteEntry register(HttpMethod method, String path, Endpoint endpoint) {
        return add(new RouteEntry(path, method, endpoint));
    }

    public RouteEntry get(String path, Page page) {
        return register(HttpMethod.GET, path, page);
    }

    public RouteEntry get(String path, Endpoint endpoint) {
        return register(HttpMethod.GET, path, endpoint);
    }

    public RouteEntry post(String path, Page page) {
        return register(HttpMethod.POST, path, page);
    }

    public RouteEntry post(String path, Endpoint endpoint) {
        return register(HttpMethod.POST, path, endpoint);
    }

    public RouteEntry put(String path, Page page) {
        return register(HttpMethod.PUT, path, page);
    }

    public RouteEntry put(String path, Endpoint endpoint) {
        return register(HttpMethod.PUT, path, endpoint);
    }

    public RouteEntry patch(String path, Page page) {
        return register(HttpMethod.PATCH, path, page);
    }

    public RouteEntry patch(String path, Endpoint endpoint) {
        return register(HttpMethod.PATCH, path, endpoint);
    }

    public RouteEntry delete(String path, Page page) {
        return register(HttpMethod.DELETE, path, page);
    }

    public RouteEntry delete(String path, Endpoint endpoint) {
        return register(HttpMethod.DELETE, path, endpoint);
    }

    /** Resolves a GET route to its page. Endpoints are not returned here. */
    public Optional<Page> resolve(String requested) {
        return resolve(HttpMethod.GET, requested);
    }

    public Optional<Page> resolve(HttpMethod method, String requested) {
        return resolveEntry(method, requested).flatMap(RouteEntry::page);
    }

    public Optional<RouteEntry> resolveEntry(String requested) {
        return resolveEntry(HttpMethod.GET, requested);
    }

    /**
     * Literal paths win over parameterized ones. Among parameterized paths, the first registered match wins.
     */
    public Optional<RouteEntry> resolveEntry(HttpMethod method, String requested) {
        RouteEntry literal = index.get(method).get(requested);
        if (literal != null) {
            return Optional.of(literal);
        }

        for (RouteEntry entry : parameterized.get(method)) {
            if (entry.matches(requested)) {
                return Optional.of(entry);
            }
        }
        return Optional.empty();
    }

    public List<RouteEntry> routes() {
        return List.copyOf(routes);
    }

    public Optional<Path> staticDir() {
        return staticDir;
    }

    public List<String> globalScripts() {
        return List.copyOf(globalScripts);
    }

    public List<String> globalStylesheets() {
        return List.copyOf(globalStylesheets);
    }

    public List<StyleSheet> globalStyles() {
        return List.copyOf(globalStyles);
    }

    public boolean usesTailwind() {
        return tailwind;
    }

    public List<String> globalTailwindCss() {
        return List.copyOf(globalTailwindCss);
    }

    private RouteEntry add(RouteEntry entry) {
        routes.add(entry);
        if (PathPattern.isParameterized(entry.path())) {
            parameterized.get(entry.method()).add(entry);
        } else {
            // First registration wins for a given method and path.
            index.get(entry.method()).putIfAbsent(entry.path(), entry);
        }
        return entry;
    }
}
