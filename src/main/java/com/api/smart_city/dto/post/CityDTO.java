package com.api.smart_city.dto.post;

import com.api.smart_city.model.City;
import lombok.Data;

@Data
public class CityDTO {
    private Long id;
    private String name;

    public CityDTO() {

    }

    public CityDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CityDTO(City city) {
        this.id = city.getId();
        this.name = city.getName();
    }
}
