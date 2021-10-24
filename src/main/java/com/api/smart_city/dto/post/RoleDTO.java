package com.api.smart_city.dto.post;

import com.api.smart_city.model.Role;
import lombok.Data;

@Data
public class RoleDTO {
    private Long id;
    private String name;

    public RoleDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }
}
