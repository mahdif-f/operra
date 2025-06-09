package rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import meta.dto.MenuItemDto;
import meta.menu.MenuItemDescriptor;
import meta.registry.CrudRegistry;
import meta.registry.MenuRegistry;

import java.util.List;
import java.util.stream.Collectors;

@Path("/meta/menu")
@Produces(MediaType.APPLICATION_JSON)
public class MenuResource {

    @Inject
    MenuRegistry menuRegistry;

    @Inject
    CrudRegistry crudRegistry;

    @GET
    public Response getMenu() {
        List<MenuItemDescriptor> items = menuRegistry.getMenu().getItems();
        List<MenuItemDto> dtoList = items.stream().map(this::mapToDto).collect(Collectors.toList());
        return Response.ok(dtoList).build();
    }

    private MenuItemDto mapToDto(MenuItemDescriptor item) {
        MenuItemDto dto = new MenuItemDto();
        dto.label = item.getLabel();
        dto.icon = item.getIcon();
        if (item.getContextClass() != null) {
            dto.context = crudRegistry.getId(item.getContextClass());
        }
        dto.children = item.getChildren().stream().map(this::mapToDto).collect(Collectors.toList());
        return dto;
    }
}