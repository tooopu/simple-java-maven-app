package com.mycompany.app;

import io.helidon.webserver.ServerConfiguration;
import java.io.IOException;
import io.helidon.webserver.Routing;
import io.helidon.webserver.WebServer;
import io.helidon.config.Config;

/**
 * Hello world!
 */
public class App {

    private static final String MESSAGE = "Hello World!";

    public App() {}

    public static void main(final String[] args) throws IOException {
        System.out.println(MESSAGE);
        executeServer();
    }

    static WebServer executeServer() throws IOException {
        Config configuration = Config.create();
        ServerConfiguration conf =
                ServerConfiguration.create(configuration.get("config"));

        WebServer server = WebServer.create(conf, routing(configuration));
        server.start()
            .thenAccept(ws -> {
                System.out.println("Service is running, give it a try on port -> " + ws.port() + " path -> {URL}/sample");
                ws.whenShutdown().thenRun(()
                    -> System.out.println("Service has been shutting down."));
                })
            .exceptionally(t -> {
                System.err.println("error: " + t.getMessage());
                t.printStackTrace(System.err);
                return null;
            });
        return server;
    }

    private static Routing routing(Config config) {

        return Routing.builder()
                .register("/sample", new SampleService())
                .build();
    }

    public String getMessage() {
        return MESSAGE;
    }
}
