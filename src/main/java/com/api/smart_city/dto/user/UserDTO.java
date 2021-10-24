package com.api.smart_city.dto.user;

import com.api.smart_city.model.User;
import com.api.smart_city.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Data
@JsonIgnoreProperties(value = { "createdAt","updatedAt" })
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    @Enumerated
    private Gender gender;
    private String email;
    private String address;
    private Collection<RoleDTO> roles = new ArrayList<>();

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate createdAt;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate updatedAt;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.gender = user.getGender();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.username = user.getUsername();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();

        user.getRoles().forEach(role -> {
            this.roles.add(new RoleDTO(role.getId(),role.getName()));
        });
    }

}
