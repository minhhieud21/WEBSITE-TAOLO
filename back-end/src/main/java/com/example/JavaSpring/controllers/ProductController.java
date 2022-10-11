package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.CategoryModel;
import com.example.JavaSpring.models.ProductModel;
import com.example.JavaSpring.service.BlogService;
import com.example.JavaSpring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/product") //localhost:8080/api/v1/blog
@CrossOrigin(origins ="http://localhost:4200")

public class ProductController {

   @Autowired
   ProductService productService;

    @GetMapping("")
    List<ProductModel> getAllProduct(){
        return productService.getAllProduct();
    }

    @GetMapping("/{id}")
    ProductModel getProductById(@PathVariable("id") String id){
        System.out.println(id);
        return productService.getProductById(id);
    }
}
