package com.api.smart_city.service;

import com.api.smart_city.model.Category;
import com.api.smart_city.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CategoryServiceImplement implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Collection<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void removeCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

}
