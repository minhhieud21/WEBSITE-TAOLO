package com.example.JavaSpring.service;

import com.example.JavaSpring.models.CategoryModel;

import java.util.List;

public interface CategoryService {
    List<CategoryModel> getAllCategory();

    CategoryModel getCateByID(String id);

    void updateCateName(String cateID, String cateName);

    void saveCate(CategoryModel cateModel);

    void statusShow(String cateID);

    void statusHide(String cateID);

}
