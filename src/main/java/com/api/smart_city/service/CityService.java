package com.api.smart_city.service;


import com.api.smart_city.model.City;

import java.util.Collection;

public interface CityService {
    public City saveCity(City city);
    public void removeCity(Long cityId);
    public Collection<City> getCities();
}
