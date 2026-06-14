package de.eztxm.blatt.routing;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Router {

    private final List<RouteEntry> routes;
    private final List<String> globalScripts;
    private final List<String> globalStylesheets;
    private Optional<Path> staticDir;

    public Router() {
        routes = new ArrayList<>();
        globalScripts = new ArrayList<>();
        globalStylesheets = new ArrayList<>();
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

    public RouteEntry register(String path, Page page) {
        RouteEntry entry = new RouteEntry(path, page);
        routes.add(entry);
        return entry;
    }

    public Optional<Page> resolve(String requested) {
        return resolveEntry(requested).map(RouteEntry::page);
    }

    public Optional<RouteEntry> resolveEntry(String requested) {
        for (RouteEntry entry : routes) {
            if (entry.matches(requested)) {
                return Optional.of(entry);
            }
        }

        return Optional.empty();
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
}
