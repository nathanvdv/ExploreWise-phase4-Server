package com.mycompany.explorewise.phase4.server.resources;

import com.mycompany.explorewise.phase4.server.models.Trips;
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
 * REST Web Service for Trips Entity
 */
@Path("trips")
public class TripsResource {

    @PersistenceContext(unitName = "ExploreWise") // Replace with your actual persistence unit name
    private EntityManager em;

    @POST
    @Path("/create")
    @Consumes({MediaType.APPLICATION_JSON})
    @Transactional
    public void create(Trips trip) {
        em.persist(trip);
    }

    @GET
    @Path("/find/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Trips find(@PathParam("id") Integer id) {
        return em.find(Trips.class, id);
    }

    @GET
    @Path("/findByCity/{cityId}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Trips> findByCityId(@PathParam("cityId") Integer cityId) {
        Query query = em.createNamedQuery("Trips.findByCityID");
        return query.setParameter("cityID", cityId).getResultList();
    }

    @GET
    @Path("/findAll")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Trips> findAll() {
        Query query = em.createNamedQuery("Trips.findAllWithReviews");
        return query.getResultList();
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Transactional
    public void edit(@PathParam("id") Integer id, Trips trip) {
        em.merge(trip);
    }

    @DELETE
    @Path("/remove/{id}")
    @Transactional
    public void remove(@PathParam("id") Integer id) {
        Trips trip = em.find(Trips.class, id);
        if (trip != null) {
            em.remove(trip);
        }
    }
}

