package com.api.smart_city.service;

import com.api.smart_city.model.Feature;
import com.api.smart_city.repository.FeatureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FeatureServiceImplement implements FeatureService{
    private final FeatureRepository featureRepository;

    @Override
    public Feature saveFeature(Feature feature) {
        return featureRepository.save(feature);
    }

    @Override
    public Collection<Feature> getFeatures() {
        return featureRepository.findAll();
    }

    @Override
    public void removeFeature(Long featureId) {
        featureRepository.deleteById(featureId);
    }
}
