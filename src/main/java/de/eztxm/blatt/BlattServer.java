package de.eztxm.blatt;

import de.eztxm.blatt.core.Document;
import de.eztxm.blatt.core.HeadConfig;
import de.eztxm.blatt.routing.Endpoint;
import de.eztxm.blatt.routing.RouteEntry;
import de.eztxm.blatt.routing.Router;
import io.javalin.Javalin;
import io.javalin.config.RoutesConfig;
import io.javalin.http.Handler;
import io.javalin.http.staticfiles.Location;

import java.util.List;

public final class BlattServer {

    private final Router router;
    private final HeadConfig baseConfig;
    private final int port;
    private Javalin app;

    public BlattServer(Router router, HeadConfig baseConfig, int port) {
        this.router = router;
        this.baseConfig = baseConfig;
        this.port = port;
    }

    public void start() {
        app = Javalin.create(config -> {
            router.staticDir().ifPresent(dir -> config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = dir.toString();
                staticFileConfig.location = Location.EXTERNAL;
            }));
            for (RouteEntry entry : router.routes()) {
                register(config.routes, entry);
            }
        });

        app.start(port);
        System.out.println("Blatt listening on http://localhost:" + port);
    }

    public void stop() {
        if (app != null) {
            app.stop();
            app = null;
        }
    }

    private void register(RoutesConfig routes, RouteEntry entry) {
        Handler handler = entry.page()
                .<Handler>map(page -> ctx -> ctx.html(new Document(page.root(), buildHeadConfig(entry)).render()))
                .orElseGet(() -> {
                    Endpoint endpoint = entry.endpoint().orElseThrow();
                    return endpoint::handle;
                });

        switch (entry.method()) {
            case GET -> routes.get(entry.path(), handler);
            case POST -> routes.post(entry.path(), handler);
            case PUT -> routes.put(entry.path(), handler);
            case PATCH -> routes.patch(entry.path(), handler);
            case DELETE -> routes.delete(entry.path(), handler);
        }
    }

    private HeadConfig buildHeadConfig(RouteEntry entry) {
        HeadConfig config = new HeadConfig()
                .title(baseConfig.title())
                .lang(baseConfig.lang());

        baseConfig.scriptSrcs().forEach(config::script);
        baseConfig.stylesheetHrefs().forEach(config::stylesheet);
        baseConfig.styles().forEach(config::style);
        enableTailwind(config, baseConfig.usesTailwind(), baseConfig.tailwindCss());
        router.globalScripts().forEach(config::script);
        router.globalStylesheets().forEach(config::stylesheet);
        router.globalStyles().forEach(config::style);
        enableTailwind(config, router.usesTailwind(), router.globalTailwindCss());
        entry.page().ifPresent(page -> config.style(page.styles()));
        entry.scripts().forEach(config::script);
        entry.stylesheets().forEach(config::stylesheet);
        entry.styles().forEach(config::style);
        enableTailwind(config, entry.usesTailwind(), entry.tailwindCss());

        return config;
    }

    private static void enableTailwind(HeadConfig config, boolean enabled, List<String> css) {
        if (!enabled) {
            return;
        }
        config.tailwind();
        css.forEach(config::tailwind);
    }
}
