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

    public  void updateCateName( String cateID ,String cateName){
        CategoryModel cateModel = categoryRepository.getCateByID(cateID);
        cateModel.setCateName(cateName);
        categoryRepository.save(cateModel);
    }

    @Override
    public void statusShow(String cateID){
        CategoryModel cateModel = categoryRepository.getCateByID(cateID);
        cateModel.setStatus(1);
        categoryRepository.save(cateModel);
    }

    @Override
    public void statusHide(String cateID) {
        CategoryModel cateModel = categoryRepository.getCateByID(cateID);
        cateModel.setStatus(0);
        categoryRepository.save(cateModel);
    }
}
