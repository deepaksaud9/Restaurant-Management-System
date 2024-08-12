package com.depp.restaurant.service.admin;

import com.depp.restaurant.dtos.CategoryDto;

import java.io.IOException;

public interface AdminService {
    CategoryDto postCategory(CategoryDto categoryDto) throws IOException;
}
