package org.acme.hibernate.orm.panache.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "breed")
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "breed_id", nullable = false)
    private Integer breed_id;

    @Column(name="breed_name", length = 40, unique = false)
    private  String breed_name;

    public enum Size {
        SMALL, MEDIUM, LARGE;
    }

    @Enumerated(EnumType.STRING)
    public Size size;

    public Integer getId() {
        return breed_id;
    }

    public void setId(Integer id) {
        this.breed_id = id;
    }

    public String getBreed_name() {
        return breed_name;
    }

    public void setBreed_name(String breed_name) {
        this.breed_name = breed_name;
    }
}