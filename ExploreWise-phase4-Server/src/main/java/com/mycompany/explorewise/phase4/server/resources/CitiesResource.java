package com.mycompany.explorewise.phase4.server.resources;

import com.mycompany.explorewise.phase4.server.models.Cities;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

/**
 * REST Web Service for Cities Entity
 */
@Path("cities")
public class CitiesResource {

    @PersistenceContext(unitName = "ExploreWise") // Change to your actual persistence unit name
    private EntityManager em;

    @POST
    @Path("/create")
    @Consumes({MediaType.APPLICATION_JSON})
    @Transactional
    public void create(Cities city) {
        em.persist(city);
    }

    @GET
    @Path("/find/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Cities find(@PathParam("id") Integer id) {
        return em.find(Cities.class, id);
    }

    @GET
    @Path("/findByName/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public Cities findByName(@PathParam("name") String cityName) {
        Query query = em.createNamedQuery("Cities.findByCityName");
        List<Cities> results = query.setParameter("cityName", cityName).getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }

    @GET
    @Path("/findAll")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Cities> findAll() {
        Query query = em.createNamedQuery("Cities.findAll");
        return query.getResultList();
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Transactional
    public void edit(@PathParam("id") Integer id, Cities city) {
        em.merge(city);
    }

    @DELETE
    @Path("/remove/{id}")
    @Transactional
    public void remove(@PathParam("id") Integer id) {
        em.remove(em.merge(em.find(Cities.class, id)));
    }
}
