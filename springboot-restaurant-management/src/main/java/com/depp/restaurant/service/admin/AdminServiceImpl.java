package com.depp.restaurant.service.admin;

import com.depp.restaurant.dtos.CategoryDto;
import com.depp.restaurant.entity.Category;
import com.depp.restaurant.mapper.CategoryMapper;
import com.depp.restaurant.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AdminServiceImpl implements AdminService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public AdminServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDto postCategory(CategoryDto categoryDto) throws IOException {

         Category category = categoryRepository.save(categoryMapper.toCategory(categoryDto));
        return categoryMapper.toCategoryDto(category);
    }
}
