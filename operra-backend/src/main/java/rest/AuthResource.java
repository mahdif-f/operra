package rest;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.auth.User;

import java.util.HashMap;
import java.util.Map;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @PersistenceContext
    EntityManager em;

    public static class LoginRequest {
        public String username;
        public String password;
    }

    @POST
    @Path("/login")
    @Transactional
    public Response login(LoginRequest request) {
        try {
            User user = em.createQuery(
                            "SELECT u FROM User u WHERE u.userName = :username AND u.password = :password", User.class)
                    .setParameter("username", request.username)
                    .setParameter("password", request.password)
                    .getSingleResult();

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("userId", user.getId());
            result.put("username", user.getUserName());

            return Response.ok(result).build();

        } catch (NoResultException e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(Map.of("success", false, "message", "نام کاربری یا رمز اشتباه است"))
                    .build();
        }
    }
}
