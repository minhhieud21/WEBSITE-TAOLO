package com.example.JavaSpring.controllers;

import com.example.JavaSpring.service.BlogService;
import com.example.JavaSpring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/product") //localhost:8080/api/v1/blog
@CrossOrigin(origins ="http://localhost:4200")

public class ProductController {

    @Autowired
    ProductService productService;

    public ProductController(ProductService productService) {
        //ssss
    }

    //test

}
