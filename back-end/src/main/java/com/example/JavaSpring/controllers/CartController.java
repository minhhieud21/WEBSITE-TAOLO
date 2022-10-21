package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.CartModel;
import com.example.JavaSpring.models.CategoryModel;
import com.example.JavaSpring.models.ProductModel;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/cart") //localhost:8080/api/v1/product
@CrossOrigin(origins ="*")

public class CartController {

    @Autowired
    CartService cartService;



}
