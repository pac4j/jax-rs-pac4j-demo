package org.pac4j.demo.jaxrs.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class IndexResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String index() {
        return "" +
                "<html><head><title>JAX-RS pac4j Demo</title></head><body>" +
                "<h1>JAX-RS pac4j Demo</h1>" +
                "<ul>" +
                "<li><a href=\"/form/index\">Protected by FormClient</a> (use login = password)</li>" +
                "<li><a href=\"/basicauth/index\">Protected by Indirect Basic Auth</a> (use login = password)</li>" +
                "<li><a href=\"/cas/index\">Protected by CAS</a> (use CAS test account)</li>" +
                "<li><a href=\"/protected/index\">Protected (any authenticated)</a></li>" +
                "<li><a href=\"/logout\">Logout</a></li>" +
                "</ul>" +
                "</body></html>";
    }
}
