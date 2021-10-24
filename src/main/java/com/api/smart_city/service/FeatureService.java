package com.api.smart_city.service;

import com.api.smart_city.model.Feature;

import java.util.Collection;

public interface FeatureService {
    public Feature saveFeature(Feature city);
    public Collection<Feature> getFeatures();
    public void removeFeature(Long featureId);
}
