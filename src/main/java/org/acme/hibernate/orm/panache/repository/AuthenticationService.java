package org.acme.hibernate.orm.panache.repository;

import io.smallrye.jwt.build.Jwt;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class AuthenticationService {

    @Transactional
    public String login(String username, String password) {
        User user = User.find("username = ?1 and password = ?2", username, password).firstResult();
        if (user != null) {
            return Jwt.issuer("https://example.com/issuer")
                    .subject(username)
                    .upn(username)
                    .groups(Set.of(user.role))  // Usa 'groups' que espera uma coleção
                    .expiresIn(3600)
                    .sign();
        }
        return null;
    }
}