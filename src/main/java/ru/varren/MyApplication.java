package ru.varren;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class MyApplication  {
    public static void main(String[] args) throws Exception {

        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        ResourceConfig config = new ResourceConfig();
        config.packages("ru.varren");
        Server server = JettyHttpContainerFactory.createServer(baseUri, config, true);

        server.join();
    }
}