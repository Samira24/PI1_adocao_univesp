package org.acme.hibernate.orm.panache.repository;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "app_user")
public class User extends PanacheEntity {
    public String username;
    public String password; // em um caso real, você deve armazenar uma hash da senha
    public String role; // 'user' ou 'admin'
}