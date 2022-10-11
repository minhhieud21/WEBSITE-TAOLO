package com.example.JavaSpring.service;

import com.example.JavaSpring.models.CategoryModel;
import com.example.JavaSpring.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryModel> getAllCategory() {
        return categoryRepository.findAll();
    }
}
