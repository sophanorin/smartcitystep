package com.api.smart_city.controller;

import com.api.smart_city.dto.post.CategoryDTO;
import com.api.smart_city.dto.post.CityDTO;
import com.api.smart_city.dto.post.FeatureDTO;
import com.api.smart_city.service.CategoryService;
import com.api.smart_city.service.CityService;
import com.api.smart_city.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/api/overall")
@RequiredArgsConstructor
public class OverallController {
    private final CategoryService categoryService;
    private final FeatureService featureService;
    private final CityService cityService;
    @GetMapping(path = "/categories")
    public ResponseEntity getCategories(){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/overall/categories").toUriString());

        Collection<CategoryDTO> categories = new ArrayList<>();
        categoryService.getCategories().forEach(category -> {
            categories.add(new CategoryDTO(category));
        });

        return ResponseEntity.created(uri).body(categories);
    }
    @GetMapping(path = "/cities")
    public ResponseEntity getCities(){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/overall/cities").toUriString());

        Collection<CityDTO> cities = new ArrayList<>();
        cityService.getCities().forEach(city -> {
            cities.add(new CityDTO(city));
        });

        return ResponseEntity.created(uri).body(cities);
    }
    @GetMapping(path = "/features")
    public ResponseEntity getFeatures(){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/overall/features").toUriString());

        Collection<FeatureDTO> features = new ArrayList<>();
        featureService.getFeatures().forEach(feature -> {
            features.add(new FeatureDTO(feature));
        });

        return ResponseEntity.created(uri).body(features);
    }
}
