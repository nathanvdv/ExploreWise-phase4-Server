package com.mycompany.explorewise.phase4.server.resources;

import com.mycompany.explorewise.phase4.server.models.Orders;
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
 * REST Web Service for Orders Entity
 */
@Path("orders")
public class OrdersResource {

    @PersistenceContext(unitName = "ExploreWise") // Replace with your actual persistence unit name
    private EntityManager em;

    @POST
    @Path("/create")
    @Consumes({MediaType.APPLICATION_JSON})
    @Transactional
    public void create(Orders order) {
        em.persist(order);
    }

    @GET
    @Path("/find/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Orders find(@PathParam("id") Integer id) {
        return em.find(Orders.class, id);
    }

    @GET
    @Path("/findByUser/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Orders> findByUserId(@PathParam("userId") Integer userId) {
        Query query = em.createNamedQuery("Orders.findByUserID");
        return query.setParameter("userID", userId).getResultList();
    }
    
    @GET
    @Path("/findByTrip/{tripId}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Orders> findByTripId(@PathParam("tripID") Integer tripId) {
        Query query = em.createNamedQuery("Orders.findByUserID");
        return query.setParameter("userID", tripId).getResultList();
    }

    @GET
    @Path("/findAll")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Orders> findAll() {
        Query query = em.createNamedQuery("Orders.findAll");
        return query.getResultList();
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Transactional
    public void edit(@PathParam("id") Integer id, Orders order) {
        em.merge(order);
    }

    @DELETE
    @Path("/remove/{id}")
    @Transactional
    public void remove(@PathParam("id") Integer id) {
        Orders order = em.find(Orders.class, id);
        if (order != null) {
            em.remove(order);
        }
    }
    
    @DELETE
    @Path("/removebytripid/{id}")
    @Transactional
    public void removebytripid(@PathParam("id") Integer id) {
        Orders order = em.find(Orders.class, id);
        if (order != null) {
            em.remove(order);
        }
    }
}

