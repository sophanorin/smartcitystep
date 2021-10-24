package com.api.smart_city.service;

import com.api.smart_city.model.Category;

import java.util.Collection;

public interface CategoryService {
    public Category saveCategory(Category category);
    public Collection<Category> getCategories();
    public void removeCategory(Long categoryId);
}
