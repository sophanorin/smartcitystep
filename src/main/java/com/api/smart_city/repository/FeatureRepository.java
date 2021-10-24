package com.api.smart_city.repository;

import com.api.smart_city.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeatureRepository extends JpaRepository<Feature,Long> {
    Optional<Feature> findByFeature(String name);
}
