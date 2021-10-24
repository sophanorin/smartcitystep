package com.api.smart_city.configs;

import com.api.smart_city.model.*;
import com.api.smart_city.model.enums.Gender;
import com.api.smart_city.service.CategoryService;
import com.api.smart_city.service.CityService;
import com.api.smart_city.service.FeatureService;
import com.api.smart_city.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfiguration {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService, FeatureService featureService, CategoryService categoryService, CityService cityService){
        return args -> {

            userService.saveRole(new Role(null,"Administrator",null));
            userService.saveRole(new Role(null,"Content_Creator",null));
            userService.saveRole(new Role(null,"Author",null));
            userService.saveRole(new Role(null,"Regular",null));

            userService.saveUser(new User("John","Travolta","john", Gender.Female,"john@test.com","street 12,PhnomPenh","1234"));
            userService.saveUser(new User("Daniel","Vigas","daniel",Gender.Male,"daniel@me.com","street 12,PhnomPenh","1234"));
            userService.saveUser(new User("Jim","Carry","jim",Gender.Female,"jim@me.com","street 122, Phnom Penh, Camobida","1234"));
            userService.saveUser(new User("Jeer","mesh","jeer",Gender.Male,"jeer@gmail.com","street 125, Kratie","1234"));

            userService.addRoleToUser("john","Administrator");
            userService.addRoleToUser("jim","Content_Creator");
            userService.addRoleToUser("daniel","Author");
            userService.addRoleToUser("jeer","Administrator");

            cityService.saveCity(new City(null,"Phnom Penh",null));
            cityService.saveCity(new City(null,"Kompong Cham",null));
            cityService.saveCity(new City(null,"Kompong Chhnang",null));
            cityService.saveCity(new City(null,"Battambong",null));
            cityService.saveCity(new City(null,"Koh Kong",null));
            cityService.saveCity(new City(null,"Ta Keo",null));
            cityService.saveCity(new City(null,"Kratie",null));

            categoryService.saveCategory( new Category(null, "Hotel", null));
            categoryService.saveCategory( new Category(null, "Travel", null));
            categoryService.saveCategory( new Category(null, "Restaurant", null));

            featureService.saveFeature(new Feature(null,"Parking",null));
            featureService.saveFeature(new Feature(null,"Dance",null));
            featureService.saveFeature(new Feature(null,"Room",null));
            featureService.saveFeature(new Feature(null,"Food",null));
            featureService.saveFeature(new Feature(null,"Club",null));
            featureService.saveFeature(new Feature(null,"Dance",null));
        };
    }
}
