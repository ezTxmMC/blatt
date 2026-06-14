package de.eztxm.blatt.http;

import de.eztxm.blatt.core.Document;
import de.eztxm.blatt.core.HeadConfig;
import de.eztxm.blatt.routing.RouteEntry;
import de.eztxm.blatt.routing.Router;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import java.util.Optional;

public final class BlattServer {

    private final Router router;
    private final HeadConfig baseConfig;
    private final int port;

    public BlattServer(Router router, HeadConfig baseConfig, int port) {
        this.router = router;
        this.baseConfig = baseConfig;
        this.port = port;
    }

    public void start() {
        Javalin app = Javalin.create(config -> {
            router.staticDir().ifPresent(dir ->
                config.staticFiles.add(dir.toString(), Location.EXTERNAL)
            );
            config.routes.get("/*", ctx -> {
                String path = ctx.path();
                Optional<RouteEntry> entry = router.resolveEntry(path);

                if (entry.isEmpty()) {
                    ctx.status(404).html("<!DOCTYPE html><html><body><h1>404 Not Found</h1></body></html>");
                    return;
                }

                HeadConfig headConfig = buildHeadConfig(entry.get());
                ctx.html(new Document(entry.get().page().root(), headConfig).render());
            });
        });

        app.start(port);
        System.out.println("Blatt listening on http://localhost:" + port);
    }

    private HeadConfig buildHeadConfig(RouteEntry entry) {
        HeadConfig config = new HeadConfig()
            .title(baseConfig.title())
            .lang(baseConfig.lang());

        router.globalScripts().forEach(config::script);
        router.globalStylesheets().forEach(config::stylesheet);
        entry.scripts().forEach(config::script);
        entry.stylesheets().forEach(config::stylesheet);

        return config;
    }
}
