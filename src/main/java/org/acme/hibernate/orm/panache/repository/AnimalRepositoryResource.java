package org.acme.hibernate.orm.panache.repository;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate.Param;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;

@Path("repository/animais")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class AnimalRepositoryResource {

    @Inject
    AnimalRepository animalRepository;
    @Inject
    BreedRepository breedRepository;
    @Inject
    AddressRepository addressRepository;

    private static final Logger LOGGER = Logger.getLogger(AnimalRepositoryResource.class.getName());




    @GET
    public List<Animal> get() {
        return animalRepository.listAll(Sort.by("name"));
    }

    @GET
    @Path("animalNames")
    public List<Animal> getAnimalNames() {

    return animalRepository.list("SELECT name from Animal");// ou usaranimalRepository.list("SELECT a.name FROM Animal a"); ou usar query SELECT name FROM Animal
    }

    @GET
    @Path("/searchByName")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Animal> searchAnimalByName(@QueryParam("name") String name) {
        // Query to fetch entire Animal objects
        //return animalRepository.list("FROM Animal where name = ?1", name);
        return animalRepository.find("name", name).list();
    }
    @GET
    @Path("animalPartialAddress")
public List<AnimalAddressDTO> getWithPartialAddress() {
    List<Animal> animals = animalRepository.listAll(Sort.by("name"));
    List<AnimalAddressDTO> result = new ArrayList<>();

    for (Animal animal : animals) {
        Address address = animal.address; // Assuming first address
        AnimalAddressDTO dto = new AnimalAddressDTO(address.street, address.city);
        result.add(dto);
    }

    return result;
}


    @GET
    @Path("{id}")
    public Animal getSingle(Long id) {
        Animal entity = animalRepository.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Animal with id of " + id + " does not exist.", 404);
        }
        return entity;
    }

    @POST
    @Transactional
    @RolesAllowed({"admin"})
    public Response create(Animal Animal) {
        if (Animal.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        animalRepository.persistAndFlush(Animal);
//        breedRepository.persist(Animal.breed);
//        addressRepository.persist(Animal.address);
        return Response.ok(Animal).status(201).build();
    }

    @PUT
    @Path("{id}")
    @RolesAllowed({"admin"})
    @Transactional
    public Animal update(Long id, Animal Animal) {
        if (Animal.name == null) {
            throw new WebApplicationException("Animal Name was not set on request.", 422);
        }

        Animal entity = animalRepository.findById(id);

        if (entity == null) {
            throw new WebApplicationException("Animal with id of " + id + " does not exist.", 404);
        }

        entity.name = Animal.name;
        entity.birth = Animal.birth;
        entity.address = Animal.address;
        entity.setHealthStatus(Animal.getHealthStatus());
        animalRepository.persistAndFlush(entity);

        return entity;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @RolesAllowed({"admin"})
    public Response delete(Long id) {
        Animal entity = animalRepository.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Animal with id of " + id + " does not exist.", 404);
        }
        animalRepository.delete(entity);
        return Response.status(204).build();
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Inject
        ObjectMapper objectMapper;

        @Override
        public Response toResponse(Exception exception) {
            LOGGER.error("Failed to handle request", exception);

            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }

            ObjectNode exceptionJson = objectMapper.createObjectNode();
            exceptionJson.put("exceptionType", exception.getClass().getName());
            exceptionJson.put("code", code);

            if (exception.getMessage() != null) {
                exceptionJson.put("error", exception.getMessage());
            }

            return Response.status(code)
                    .entity(exceptionJson)
                    .build();
        }

    }
}
