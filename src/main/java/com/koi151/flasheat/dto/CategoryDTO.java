package com.koi151.flasheat.dto;

import java.util.List;

public class CategoryDTO {
    private String name;
    private List<MenuDTO> menus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuDTO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDTO> menus) {
        this.menus = menus;
    }
}
