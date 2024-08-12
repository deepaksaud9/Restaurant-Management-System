package com.depp.restaurant.controller;

import com.depp.restaurant.dtos.CategoryDto;
import com.depp.restaurant.service.admin.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryDto> postCategory(@ModelAttribute CategoryDto categoryDto) throws IOException {
        CategoryDto createdCategoryDto = adminService.postCategory(categoryDto);
        if (categoryDto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(createdCategoryDto,HttpStatus.CREATED);
    }
}
