package org.acme.hibernate.orm.panache.repository;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthenticationResource {

    @Inject
    AuthenticationService authService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        String token = authService.login(username, password);
        if (token != null) {
            return Response.ok(token).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}