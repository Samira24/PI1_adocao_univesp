package org.acme.hibernate.orm.panache.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.*;
import net.bytebuddy.asm.Advice;

@Entity
@Cacheable
public class Animal {


    @Id
    @GeneratedValue
    public Long id;

    @Column(length = 40, unique = false)
    public String name;



    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="breed_id")
    public Breed breed;

    @Column(length = 20, unique = false)
    public LocalDate birth;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "address_id", nullable = false)
    public Address address; // One Animal -> Many Addresses




    @Column(name="health_status")
    private String healthStatus;

    public static final List<String> VALID_HEALTH_STATUS = Arrays.asList("Saudável", "Saudável com limitações", "Não saudável");



    public Animal() {
    }   

    public Animal(String name, Address address, Breed breed, LocalDate birth) {
        this.name = name;
        this.address = address ; // Initialize addresse
        this.breed = breed;
        this.birth=birth;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
   /*     if (!VALID_HEALTH_STATUS.contains(healthStatus.toLowerCase())) {
            throw new IllegalArgumentException("Invalid health status: " + healthStatus + ". Valid options are: " + VALID_HEALTH_STATUS);
        }*/
        this.healthStatus = healthStatus;
    }
}
