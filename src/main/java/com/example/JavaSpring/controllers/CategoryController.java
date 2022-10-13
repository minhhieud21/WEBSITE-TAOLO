package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.CategoryModel;
import com.example.JavaSpring.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category") //localhost:8080/api/v1/category
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    
    @GetMapping("")
    List<CategoryModel> getAllCategory(){
        return categoryService.getAllCategory();
    }

}
