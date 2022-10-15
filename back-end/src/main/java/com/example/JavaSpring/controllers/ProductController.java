package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.BlogModel;
import com.example.JavaSpring.models.ProductModel;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/product") //localhost:8080/api/v1/product
@CrossOrigin(origins ="http://localhost:4200")

public class ProductController {

    @Autowired
    ProductService productService;

    // GET all : localhost:8080/api/v1/product
    @GetMapping("getAllProductAdmin")
    ResponseEntity<ResponseObject> getAllProductAdmin() {
        List<ProductModel> check = productService.getAllProduct();
        return check.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(false, "")
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(true, check)
                );
    }

    @GetMapping("getAllProductUser")
    ResponseEntity<ResponseObject> getAllProductUser() {
        List<ProductModel> kq = productService.getAllProduct();
        List<ProductModel> check =  new ArrayList<ProductModel>();
        for (int i = 0; i < kq.size();i++){
            if(kq.get(i).getStatus() == 1 ){
                check.add(kq.get(i));
            }
        }
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
    //localhost:8080/api/v1/product/getProduct?cateId=?
    @GetMapping("/getProduct")
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

    @DeleteMapping("/{proId}") /// Đang viết lõ nen để tạm đây
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable String proId){
        productService.deleteProduct(proId);
        return null;
//        if(check.isPresent() == true){
//                return ResponseEntity.status(HttpStatus.OK).body(
//                        new ResponseObject(true,"")
//                );}
//        else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                        new ResponseObject(false, "")
//                );}
    }

    @PostMapping("/statusHide")
    ResponseEntity<ResponseObject> statusHide(@RequestParam(required = false) String proId){
        ProductModel productModel = productService.getProductById(proId);
        Optional<ProductModel> check = Optional.ofNullable(productModel);
        if(check.isPresent() == true && productModel.getStatus() != 0){
            productService.statusHide(proId);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(true,"")
                );}
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(false, "")
                );}
    }

    @PostMapping("/statusShow")
    ResponseEntity<ResponseObject> statusShow(@RequestParam(required = false) String proId){
        ProductModel productModel = productService.getProductById(proId);
        Optional<ProductModel> check = Optional.ofNullable(productModel);
        if(check.isPresent() == true && productModel.getStatus() != 1){
            productService.statusShow(proId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(true,"")
            );}
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(false, "")
            );}
    }

    @PostMapping("/setPrice")
    ResponseEntity<ResponseObject> setPrice(@RequestParam(required = false) String proId,@RequestParam(required = false) Long price){
        ProductModel productModel = productService.getProductById(proId);
        Optional<ProductModel> check = Optional.ofNullable(productModel);
        if(check.isPresent() == true && productModel.getPrice() != price){
            productService.updatePrice(proId,price);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(true,"")
            );}
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(false, "")
            );}
    }
}