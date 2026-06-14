package de.eztxm.blatt.http;

import de.eztxm.blatt.core.Document;
import de.eztxm.blatt.core.HeadConfig;
import de.eztxm.blatt.routing.RouteEntry;
import de.eztxm.blatt.routing.Router;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

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
                config.staticFiles.add(staticFileConfig -> {
                    staticFileConfig.hostedPath = "/";
                    staticFileConfig.directory = dir.toString();
                    staticFileConfig.location = Location.EXTERNAL;
                })
            );
            for (RouteEntry entry : router.routes()) {
                config.routes.get(entry.path(), ctx ->
                    ctx.html(new Document(entry.page().root(), buildHeadConfig(entry)).render())
                );
            }
        });

        app.start(port);
        System.out.println("Blatt listening on http://localhost:" + port);
    }

    private HeadConfig buildHeadConfig(RouteEntry entry) {
        HeadConfig config = new HeadConfig()
            .title(baseConfig.title())
            .lang(baseConfig.lang());

        baseConfig.scriptSrcs().forEach(config::script);
        baseConfig.stylesheetHrefs().forEach(config::stylesheet);
        router.globalScripts().forEach(config::script);
        router.globalStylesheets().forEach(config::stylesheet);
        entry.scripts().forEach(config::script);
        entry.stylesheets().forEach(config::stylesheet);

        return config;
    }
}
