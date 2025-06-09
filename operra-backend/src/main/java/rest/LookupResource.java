package rest;


import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import meta.core.LookupDescriptor;
import meta.registry.CrudRegistry;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/meta/lookup")
@Produces(MediaType.APPLICATION_JSON)
public class LookupResource {

    @Inject
    CrudRegistry registry;

    @PersistenceContext
    EntityManager em;

    @GET
    @Path("/{lookupId}")
    public Response getLookup(@PathParam("lookupId") String lookupId) {
        for (var ctx : registry.getAll().values()) {
            LookupDescriptor descriptor = ctx.getLookups().get(lookupId);
            if (descriptor != null) {
                Query query = em.createQuery(descriptor.getQuery());
                List<Object[]> resultList = query.getResultList();

                List<Map<String, Object>> results = resultList.stream().map(row -> {
                    Map<String, Object> map = Map.of(
                            "value", row[0],
                            "title", row[1]
                    );
                    return map;
                }).collect(Collectors.toList());

                return Response.ok(results).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Lookup not found: " + lookupId).build();
    }
}