package org.acme.hibernate.orm.panache.repository;

// New DTO
public class AnimalAddressDTO {
  
    public String street;
    public String city;
    public AnimalAddressDTO(String street2, String city2) {
        street = street2;
        city = city2;
    }
}
