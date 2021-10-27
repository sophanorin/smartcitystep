package com.api.smart_city.dto.post;

import com.api.smart_city.model.Feature;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FeatureDTO {
    private Long id;
    private String feature;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public FeatureDTO() {
    }

    public FeatureDTO(Long id, String feature) {
        this.id = id;
        this.feature = feature;
    }
    public FeatureDTO(Feature feature) {
        this.id = feature.getId();
        this.feature = feature.getFeature();
        this.createdAt = feature.getCreatedAt();
        this.updatedAt = feature.getUpdatedAt();
    }
}
