package com.api.smart_city.dto.post;


import com.api.smart_city.model.Category;
import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;
    private String category;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String category) {
        this.id = id;
        this.category = category;
    }
    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.category = category.getCategory();
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", category='" + category +
                '}';
    }
}
