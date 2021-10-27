package com.api.smart_city.dto.post;


import com.api.smart_city.model.Category;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CategoryDTO {
    private Long id;
    private String category;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String category) {
        this.id = id;
        this.category = category;
    }
    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.category = category.getCategory();
        this.createdAt = category.getCreatedAt();
        this.updatedAt = category.getUpdatedAt();
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", category='" + category +
                '}';
    }
}
