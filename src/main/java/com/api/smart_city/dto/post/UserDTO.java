package com.api.smart_city.dto.post;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;

    public UserDTO() {
    }

    public UserDTO(Long id, String firstName,String lastName, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }
}
