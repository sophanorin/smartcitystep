package com.api.smart_city.repository;

import com.api.smart_city.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByCategory (String category);
}
