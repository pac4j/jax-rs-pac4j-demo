package org.pac4j.demo.jaxrs.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.pac4j.core.authorization.authorizer.DefaultAuthorizers;
import org.pac4j.jax.rs.annotations.Pac4JProfile;
import org.pac4j.jax.rs.annotations.Pac4JSecurity;
import org.pac4j.core.profile.CommonProfile;

@Path("/protected")
public class ProtectedResource {

    private String page(String title, CommonProfile profile) {
        final String id = profile != null ? String.valueOf(profile.getId()) : "anonymous";
        return "<html><body><h2>" + title + "</h2>" +
               "<p>Authenticated as: " + id + "</p>" +
               "<p><a href=\"/\">Home</a></p>" +
               "</body></html>";
    }

    @GET
    @Path("/index")
    @Produces(MediaType.TEXT_HTML)
    @Pac4JSecurity(authorizers = DefaultAuthorizers.IS_AUTHENTICATED)
    public String any(@Pac4JProfile CommonProfile profile) {
        return page("Protected (any authenticated)", profile);
    }

    @GET
    @Path("/form")
    @Produces(MediaType.TEXT_HTML)
    public String form() {
        return "<html><body><h2>Login Form</h2>" +
               "<form method=\"post\" action=\"/callback?client_name=FormClient\">" +
               "<input type=\"text\" name=\"username\" placeholder=\"username\"/>" +
               "<br/><input type=\"password\" name=\"password\" placeholder=\"password\"/>" +
               "<br/><input type=\"submit\" value=\"Login\"/>" +
               "</form><p><a href=\"/\">Home</a></p></body></html>";
    }
}
