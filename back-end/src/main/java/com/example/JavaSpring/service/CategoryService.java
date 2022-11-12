package com.example.JavaSpring.service;

import com.example.JavaSpring.models.CategoryModel;

import java.util.List;

public interface CategoryService {
    List<CategoryModel> getAllCategory();

    CategoryModel getCateByID(String id);

    void updateCategory(String cateID, String cateName, int status);

    void saveCate(CategoryModel cateModel);

}
