package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.BlogModel;
import com.example.JavaSpring.models.ProductModel;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/product") //localhost:8080/api/v1/product
@CrossOrigin(origins ="http://localhost:4200")

public class ProductController {

    @Autowired
    ProductService productService;

    // GET all : localhost:8080/api/v1/product
    @GetMapping("")
    ResponseEntity<ResponseObject> getAllProduct() {
        List<ProductModel> check = productService.getAllProduct();
        return check.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(false, "")
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(true, check)
                );
    }

    // GET by id: localhost:8080/api/v1/product/:id
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getProductById(@PathVariable("id") String id) {
        Optional<ProductModel> check = Optional.ofNullable(productService.getProductById(id));
        return check.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(true, check)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(false, "")
                );
    }

    @GetMapping("/getProduct")
        //localhost:8080/api/v1/product/getProduct?cateId=?
    ResponseEntity<ResponseObject> getProductByCateID(@RequestParam(required = false) String cateId) {
        List<ProductModel> check = productService.getProductByCateID(cateId);
        return check.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(false, "")
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(true, check)
                );
    }

    // POST : localhost:8080/api/v1/product/add
    @PostMapping("/add")
    ResponseEntity<ResponseObject> addnewProduct(@RequestBody ProductModel productModel) {
        Optional<ProductModel> check = Optional.ofNullable(productService.getProductById(productModel.getproId()));
        if (check.isPresent() == true) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(false, "")
            );
        } else {
            List<ProductModel> list = productService.getAllProduct();
            Long max = Long.valueOf(0);
            for (int i=0;i<list.size();i++){
                if(max < list.get(i).get_id()){
                    max = list.get(i).get_id();
                }
            }
            productModel.set_id(max+1);
            productService.saveProduct(productModel);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(true, "")
            );
        }
    }

    @PostMapping("/showHide")
    ResponseEntity<ResponseObject> showHide(@RequestParam(required = false) String proId) {
        Optional<ProductModel> check = Optional.ofNullable(productService.getProductById(proId));
        System.out.println(check);
        if(check.isPresent() == true){
                productService.showHide(proId);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(true, "")
                );}
        else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(false, "")
                );}
    }
}