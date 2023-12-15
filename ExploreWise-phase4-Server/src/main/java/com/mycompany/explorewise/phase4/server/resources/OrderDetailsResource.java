package com.mycompany.explorewise.phase4.server.resources;

import com.mycompany.explorewise.phase4.server.models.OrderDetails;
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
 * REST Web Service for OrderDetails Entity
 */
@Path("orderdetails")
public class OrderDetailsResource {

    @PersistenceContext(unitName = "ExploreWise") // Change to your actual persistence unit name
    private EntityManager em;

    @POST
    @Path("/create")
    @Consumes({MediaType.APPLICATION_JSON})
    @Transactional
    public void create(OrderDetails orderDetail) {
        em.persist(orderDetail);
    }

    @GET
    @Path("/find/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public OrderDetails find(@PathParam("id") Integer id) {
        return em.find(OrderDetails.class, id);
    }

    @GET
    @Path("/findByOrder/{orderId}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<OrderDetails> findByOrderId(@PathParam("orderId") Integer orderId) {
        Query query = em.createNamedQuery("OrderDetails.findByOrderID");
        return query.setParameter("orderID", orderId).getResultList();
    }

    @GET
    @Path("/findAll")
    @Produces({MediaType.APPLICATION_JSON})
    public List<OrderDetails> findAll() {
        Query query = em.createNamedQuery("OrderDetails.findAll");
        return query.getResultList();
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Transactional
    public void edit(@PathParam("id") Integer id, OrderDetails orderDetail) {
        em.merge(orderDetail);
    }

    @DELETE
    @Path("/remove/{id}")
    @Transactional
    public void remove(@PathParam("id") Integer id) {
        em.remove(em.merge(em.find(OrderDetails.class, id)));
    }
}
