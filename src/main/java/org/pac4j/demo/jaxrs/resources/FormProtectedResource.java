package org.pac4j.demo.jaxrs.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.pac4j.core.authorization.authorizer.DefaultAuthorizers;
import org.pac4j.jax.rs.annotations.Pac4JProfile;
import org.pac4j.jax.rs.annotations.Pac4JSecurity;
import org.pac4j.core.profile.CommonProfile;

@Path("/form")
public class FormProtectedResource {

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
    @Pac4JSecurity(clients = "FormClient", authorizers = DefaultAuthorizers.IS_AUTHENTICATED)
    public String formProtected(@Pac4JProfile CommonProfile profile) {
        return page("Form Protected", profile);
    }
}
