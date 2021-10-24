package com.api.smart_city.dto.post;

import com.api.smart_city.model.Feature;
import lombok.Data;

@Data
public class FeatureDTO {
    private Long id;
    private String feature;

    public FeatureDTO() {
    }

    public FeatureDTO(Long id, String feature) {
        this.id = id;
        this.feature = feature;
    }
    public FeatureDTO(Feature feature) {
        this.id = feature.getId();
        this.feature = feature.getFeature();
    }
}
