package rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;
import meta.core.LookupDescriptor;
import meta.dto.CrudContextDto;
import meta.dto.FieldDto;
import meta.dto.LookupDto;
import meta.dto.RuleDto;
import meta.registry.CrudRegistry;
import meta.core.CrudContext;

import java.util.*;
import java.util.stream.Collectors;

@Path("/meta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CrudMetaResource {

    @Inject
    CrudRegistry registry;

    @GET
    @Path("/entities")
    public Response listEntities() {
        List<String> names = registry.getAll().keySet().stream()
                .map(Class::getSimpleName)
                .sorted()
                .collect(Collectors.toList());
        return Response.ok(names).build();
    }

    @GET
    @Path("/context/{entity}")
    public Response getContext(@PathParam("entity") String entity) {
        CrudContext<?> context = registry.getContext(entity);
        CrudContextDto dto = toDto(context);
        return Response.ok(dto).build();
    }

    private CrudContextDto toDto(CrudContext<?> context) {
        CrudContextDto dto = new CrudContextDto();
        dto.setTitle(context.getTitle());
        dto.setFormType(context.getFormType().name());

        List<FieldDto> fieldDtos = context.getFields().stream().map(f -> {
            FieldDto fd = new FieldDto();
            fd.setName(f.getName());
            fd.setLabel(f.getLabel());
            fd.setType(f.getType().name());
            fd.setRequired(f.isRequired());
            fd.setReadonly(f.isReadonly());
            return fd;
        }).collect(Collectors.toList());
        dto.setFields(fieldDtos);

        List<RuleDto> ruleDtos = context.getRules().stream().map(r -> {
            RuleDto rd = new RuleDto();
            rd.setType(r.getType().name());
            rd.setTargetField(r.getTargetField());
            rd.setExpression(r.getExpression());
            rd.setTrigger(r.getTrigger().name());
            return rd;
        }).collect(Collectors.toList());
        dto.setRules(ruleDtos);

        List<LookupDto> lookupDtos = context.getLookups().entrySet().stream().map(e -> {
            LookupDescriptor l = e.getValue();
            LookupDto ld = new LookupDto();
            ld.setId(l.getId());
            ld.setTitle(l.getTitle());
            ld.setColumns(l.getColumns());
            ld.setParameters(l.getParameters());
            return ld;
        }).collect(Collectors.toList());
        dto.setLookups(lookupDtos);

        return dto;
    }
}