package org.pac4j.demo.jaxrs.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.pac4j.jax.rs.annotations.Pac4JCallback;
import org.pac4j.jax.rs.annotations.Pac4JLogout;

@Path("/")
public class CallbackAndLogoutResource {

    // RenewSession=false to avoid double cookie with Grizzly (mirrors tests in jax-rs-pac4j)
    @POST
    @Path("callback")
    @Pac4JCallback(defaultUrl = "/", renewSession = false)
    public void callbackPost() {
        // handled by filter
    }

    @GET
    @Path("callback")
    @Pac4JCallback(defaultUrl = "/", renewSession = false)
    public void callbackGet() {
        // handled by filter
    }

    @GET
    @Path("logout")
    @Pac4JLogout(destroySession = true)
    public void logout() {
        // handled by filter
    }
}
