package org.acme.hibernate.orm.panache.repository;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long address_id;

    public String street;
    public String neighborhood;
    public String city;

    public String number;


   /*  @OneToMany // Represents the many side of the relationship
     @JoinColumn(name = "address_id_list")
     public List<Animal> animal; // Foreign key reference to the Animal*/

    public Address() {
    }

    public Address(String street, String neighborhood, String city) {
        this.street = street;
        this.neighborhood = neighborhood;
        this.city = city;
        // this.animal = new ArrayList<>();
    }
}