package org.acme.hibernate.orm.panache.repository;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/secure")
public class SecureResource {

    @GET
    @RolesAllowed({"admin"})
    public String secureMethod() {
        return "Acesso autorizado!";
    }
}