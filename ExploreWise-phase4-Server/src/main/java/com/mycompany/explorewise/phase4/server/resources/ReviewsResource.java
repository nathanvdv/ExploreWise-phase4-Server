package com.mycompany.explorewise.phase4.server.resources;

import com.mycompany.explorewise.phase4.server.models.Reviews;
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
 * REST Web Service for Reviews Entity
 */
@Path("reviews")
public class ReviewsResource {

    @PersistenceContext(unitName = "ExploreWise") // Replace with your actual persistence unit name
    private EntityManager em;

    @POST
    @Path("/create")
    @Consumes({MediaType.APPLICATION_JSON})
    @Transactional
    public void create(Reviews review) {
        em.persist(review);
    }

    @GET
    @Path("/find/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Reviews find(@PathParam("id") Integer id) {
        return em.find(Reviews.class, id);
    }

    @GET
    @Path("/findByTrip/{tripID}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Reviews> findByTripId(@PathParam("tripID") Integer tripID) {
        Query query = em.createNamedQuery("Reviews.findByTripID");
        return query.setParameter("tripID", tripID).getResultList();
    }
    
    @GET
    @Path("/findByTripAndUser/{tripId}/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Reviews> findByTripAndUser(
            @PathParam("tripId") Integer tripId,
            @PathParam("userId") Integer userId) {
        Query query = em.createNamedQuery("Reviews.findByTripAndUser");
        return query.setParameter("tripID", tripId)
                    .setParameter("userID", userId)
                    .getResultList();
    }

    @GET
    @Path("/findAll")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Reviews> findAll() {
        Query query = em.createNamedQuery("Reviews.findAll");
        return query.getResultList();
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Transactional
    public void edit(@PathParam("id") Integer id, Reviews review) {
        em.merge(review);
    }

    @DELETE
    @Path("/remove/{id}")
    @Transactional
    public void remove(@PathParam("id") Integer id) {
        Reviews review = em.find(Reviews.class, id);
        if (review != null) {
            em.remove(review);
        }
    }
    
    @DELETE
    @Path("/removeByUserID/{id}")
    @Transactional
    public void removeByUserID(@PathParam("id") Integer id) {
        Reviews review = em.find(Reviews.class, id);
        if (review != null) {
            em.remove(review);
        }
    }
}

