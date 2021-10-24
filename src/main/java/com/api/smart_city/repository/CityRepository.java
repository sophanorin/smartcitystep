package com.api.smart_city.repository;

import com.api.smart_city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
    City findByName(String name);
}
