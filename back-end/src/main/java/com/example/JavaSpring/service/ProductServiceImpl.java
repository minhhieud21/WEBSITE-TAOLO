package com.example.JavaSpring.service;

import com.example.JavaSpring.models.ProductModel;
import com.example.JavaSpring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductModel> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public ProductModel getProductById(String id) {
        return productRepository.getProductByID(id);
    }
}
