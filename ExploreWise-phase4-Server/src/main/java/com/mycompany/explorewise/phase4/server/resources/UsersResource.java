package com.mycompany.explorewise.phase4.server.resources;

import com.mycompany.explorewise.phase4.server.models.Users;
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
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * REST Web Service for Users Entity
 */
@Path("users")
public class UsersResource {

    @PersistenceContext(unitName = "ExploreWise")
    private EntityManager em;

    @POST
    @Path("/create")
    @Consumes({MediaType.APPLICATION_JSON})
    @Transactional
    public void create(Users user) {
        em.persist(user);
    }

    @GET
    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findUserById(@PathParam("id") int id) {
        Users user = em.find(Users.class, id);
        if (user != null) {
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found with ID: " + id).build();
        }
    }

    @GET
    @Path("/findByUsername/{username}")
    @Produces({MediaType.APPLICATION_JSON})
    public Users findByUsername(@PathParam("username") String username) {
        Query query = em.createNamedQuery("Users.findByUsername");
        List<Users> users = query.setParameter("username", username).getResultList();
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    @GET
    @Path("/findAll")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Users> findAll() {
        Query query = em.createNamedQuery("Users.findAll");
        return query.getResultList();
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Transactional
    public void edit(@PathParam("id") Integer id, Users user) {
        em.merge(user);
    }

    @DELETE
    @Path("/remove/{id}")
    @Transactional
    public void remove(@PathParam("id") Integer id) {
        Users user = em.find(Users.class, id);
        if (user != null) {
            em.remove(user);
        }
    }
}

