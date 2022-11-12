package com.example.JavaSpring.service;

import com.example.JavaSpring.models.CategoryModel;
import com.example.JavaSpring.models.ProductModel;
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

    @Override
    public CategoryModel getCateByID(String cateId) {
        return categoryRepository.getCateByID(cateId);
    }

    @Override
    public void saveCate(CategoryModel cateModel){
        categoryRepository.save(cateModel);
    }

   @Override
    public void updateCategory(String cateID, String cateName, int status){
        CategoryModel categoryModel = categoryRepository.getCateByID(cateID);
        if(categoryModel.getCateName() != cateName){
            categoryModel.setCateName(cateName);
        }
        if(categoryModel.getStatus() != status){
            categoryModel.setStatus(status);
        }
        categoryRepository.save(categoryModel);
   }
}
