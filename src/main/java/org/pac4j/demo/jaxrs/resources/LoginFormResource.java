package org.pac4j.demo.jaxrs.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class LoginFormResource {

    @GET
    @Path("loginForm")
    @Produces(MediaType.TEXT_HTML)
    public String loginForm() {
        return "<html><body>"
            + "<h2>Login Form (FormClient)</h2>"
            + "<form method=\"post\" action=\"/callback?client_name=FormClient\">"
            + "<input type=\"text\" name=\"username\" placeholder=\"username\"/>"
            + "<br/><input type=\"password\" name=\"password\" placeholder=\"password\"/>"
            + "<br/><input type=\"submit\" value=\"Login\"/>"
            + "</form>"
            + "<p><a href=\"/\">Home</a></p>"
            + "</body></html>";
    }
}
