package de.eztxm.blatt.examples;

import de.eztxm.blatt.core.HeadConfig;
import de.eztxm.blatt.http.BlattServer;
import de.eztxm.blatt.routing.Router;
import de.eztxm.blatt.routing.RouteScanner;

import java.nio.file.Paths;

public final class ExampleApp {

    public static void main(String[] args) throws Exception {
        Router router = new Router()
            .staticFiles(Paths.get("public"));

        new RouteScanner("de.eztxm.blatt.examples").scan(router);

        HeadConfig head = new HeadConfig()
            .title("Blatt Example");

        new BlattServer(router, head, 7171).start();
    }
}
