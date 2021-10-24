package com.api.smart_city.dto.postRequest;

import lombok.Data;
@Data
public class LocationRequestDTO {
    private Long id;
    private String mapb_location;
    private Long cityId;

    public LocationRequestDTO() {
    }

    public LocationRequestDTO(Long id, String mapb_location, Long cityId) {
        this.id = id;
        this.mapb_location = mapb_location;
        this.cityId = cityId;
    }

}
