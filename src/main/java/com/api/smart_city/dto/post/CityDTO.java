package com.api.smart_city.dto.post;

import com.api.smart_city.model.City;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CityDTO {
    private Long id;
    private String name;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public CityDTO() {

    }

    public CityDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CityDTO(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.createdAt = city.getCreatedAt();
        this.updatedAt = city.getUpdatedAt();
    }
}
