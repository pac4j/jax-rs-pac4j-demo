package org.pac4j.demo.jaxrs;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.core.client.Clients;
import org.pac4j.core.client.direct.AnonymousClient;
import org.pac4j.core.config.Config;
import org.pac4j.http.client.indirect.FormClient;
import org.pac4j.http.client.indirect.IndirectBasicAuthClient;
import org.pac4j.http.credentials.authenticator.test.SimpleTestUsernamePasswordAuthenticator;
import org.pac4j.jax.rs.features.Pac4JSecurityFeature;
import org.pac4j.jax.rs.grizzly.features.Pac4JGrizzlyFeature;
import org.pac4j.jax.rs.jersey.features.Pac4JValueFactoryProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        final int port = 8080;
        final String baseUrl = "http://localhost:" + port;

        // Build pac4j Clients
        final var authenticator = new SimpleTestUsernamePasswordAuthenticator();
        final var formClient = new FormClient(baseUrl + "/loginForm", authenticator);
        final var basicClient = new IndirectBasicAuthClient(authenticator);

        // CAS configuration (same as other demos)
        final var casConfig = new CasConfiguration("https://casserverpac4j.herokuapp.com/login");
        final var casClient = new CasClient(casConfig);

        final var clients = new Clients(baseUrl + "/callback", formClient, basicClient, casClient, new AnonymousClient());
        final var config = new Config(clients);

        final ResourceConfig rc = new ResourceConfig();
        rc.register(new Pac4JGrizzlyFeature(config));
        rc.register(new Pac4JSecurityFeature());
        rc.register(new Pac4JValueFactoryProvider.Binder());

        // Resources
        rc.packages("org.pac4j.demo.jaxrs.resources");

        final URI uri = URI.create(baseUrl + "/");
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, rc, false);
        for (NetworkListener listener : server.getListeners()) {
            listener.getTransport().getWorkerThreadPoolConfig().setCorePoolSize(2);
            listener.getTransport().getWorkerThreadPoolConfig().setMaxPoolSize(8);
        }

        try {
            server.start();
            LOG.info("Server started on {}", baseUrl);
            Runtime.getRuntime().addShutdownHook(new Thread(server::shutdownNow));
            Thread.currentThread().join();
        } catch (Exception e) {
            LOG.error("Failed to start server", e);
            server.shutdownNow();
        }
    }
}
