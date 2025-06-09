package meta.dto;

import java.util.ArrayList;
import java.util.List;

public class MenuItemDto {
    public String label;
    public String icon;
    public String context;
    public List<MenuItemDto> children = new ArrayList<>();
}