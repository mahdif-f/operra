package rest;


import core.CrudEngine;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import meta.registry.CrudRegistry;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/crud")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CrudResource {

    @Inject
    CrudEngine engine;

    @Inject
    CrudRegistry registry;

    Jsonb jsonb = JsonbBuilder.create();

    private <T> Class<T> resolveEntityClass(String name) {
        return (Class<T>) registry.getAll().keySet().stream()
                .filter(c -> c.getSimpleName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Entity not found: " + name));
    }

    @GET
    @Path("/{entity}")
    public Response list(@PathParam("entity") String entity) {
        Class<?> entityClass = resolveEntityClass(entity);
        return Response.ok(engine.list(entityClass)).build();
    }

    @GET
    @Path("/{entity}/{id}")
    public Response get(@PathParam("entity") String entity, @PathParam("id") Long id) {
        Class<?> entityClass = resolveEntityClass(entity);
        return Response.ok(engine.get(entityClass, id)).build();
    }

    @POST
    @Path("/{entity}")
    @Transactional
    public Response create(@PathParam("entity") String entity, String payload) {
        Class<?> entityClass = resolveEntityClass(entity);
        Object obj = jsonb.fromJson(payload, entityClass);
        Object result = invokeCreate(entityClass, obj);
        return Response.ok(result).build();
    }

    @PUT
    @Path("/{entity}/{id}")
    @Transactional
    public Response update(@PathParam("entity") String entity, @PathParam("id") Long id, String payload) {
        Class<?> entityClass = resolveEntityClass(entity);
        Object obj = jsonb.fromJson(payload, entityClass);
        Object result = invokeUpdate(entityClass, obj);
        return Response.ok(result).build();
    }

    @DELETE
    @Path("/{entity}/{id}")
    @Transactional
    public Response delete(@PathParam("entity") String entity, @PathParam("id") Long id) {
        Class<?> entityClass = resolveEntityClass(entity);
        engine.delete(entityClass, id);
        return Response.noContent().build();
    }

    private <T> T invokeCreate(Class<T> entityClass, Object obj) {
        return engine.create(entityClass, entityClass.cast(obj));
    }

    private <T> T invokeUpdate(Class<T> entityClass, Object obj) {
        return engine.update(entityClass, entityClass.cast(obj));
    }
}