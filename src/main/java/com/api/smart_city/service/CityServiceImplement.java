package com.api.smart_city.service;

import com.api.smart_city.model.City;
import com.api.smart_city.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CityServiceImplement implements CityService{
    private final CityRepository cityRepository;
    @Override
    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void removeCity(Long cityId) {
        cityRepository.deleteById(cityId);
    }

    @Override
    public Collection<City> getCities() {
        return cityRepository.findAll();
    }

}
