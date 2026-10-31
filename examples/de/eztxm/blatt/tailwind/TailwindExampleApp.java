package de.eztxm.blatt.tailwind;

import de.eztxm.blatt.BlattServer;
import de.eztxm.blatt.core.HeadConfig;
import de.eztxm.blatt.routing.RouteScanner;
import de.eztxm.blatt.routing.Router;

/** Runs the Tailwind example on port 7172. Tailwind is experimental (browser build). */
public final class TailwindExampleApp {

    public static void main(String[] args) {
        Router router = new Router()
            .tailwind("""
                @theme {
                  --color-brand: #4338ca;
                }
                """);

        new RouteScanner("de.eztxm.blatt.tailwind").scan(router);

        new BlattServer(router, new HeadConfig().title("Tailwind on Blatt"), 7172).start();
    }
}
