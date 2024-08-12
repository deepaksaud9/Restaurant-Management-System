package com.depp.restaurant.mapper;

import com.depp.restaurant.dtos.CategoryDto;
import com.depp.restaurant.entity.Category;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CategoryMapper {

    public Category toCategory(CategoryDto categoryDto) throws IOException {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(category.getDescription());
        category.setImage(categoryDto.getImg().getBytes());
        return category;
    }

    public CategoryDto toCategoryDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
       /* categoryDto.setName(categoryDto.getName());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setImg(category.getImage());*/
        return categoryDto;
    }
}
