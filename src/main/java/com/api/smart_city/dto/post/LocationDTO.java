package com.api.smart_city.dto.post;

import lombok.Data;

@Data
public class LocationDTO {
    private Long id;
    private String mapb_location;
    private CityDTO city;

    public LocationDTO() {
    }

    public LocationDTO(Long id, String mapb_location, CityDTO city) {
        this.id = id;
        this.mapb_location = mapb_location;
        this.city = city;
    }
}
