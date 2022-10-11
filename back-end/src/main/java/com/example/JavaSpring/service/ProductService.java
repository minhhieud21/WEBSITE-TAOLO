package com.example.JavaSpring.service;

import com.example.JavaSpring.models.ProductModel;

import java.util.List;

public interface ProductService {
    List<ProductModel> getAllProduct();

    ProductModel getProductById(String id);
}
