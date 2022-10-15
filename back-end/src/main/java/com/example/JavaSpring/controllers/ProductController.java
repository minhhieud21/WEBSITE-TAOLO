package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.ProductModel;
import com.example.JavaSpring.util.Error;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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

    // GET all : localhost:8080/api/v1/product/getAllProductAdmin?page=1
    @GetMapping("getAllProductAdmin")
    ResponseEntity<ResponseObject>getAllProductAdmin(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int size){
        Pageable paging = PageRequest.of(page,size);
        model.addAttribute("product",productService.getAllProductAdmin(paging));
        Page<ProductModel> check = productService.getAllProductAdmin(paging);
        return check.isEmpty() ?
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,"")
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                );
    }

    // GET all : localhost:8080/api/v1/product/getAllProductUser
    @GetMapping("getAllProductUser")
    ResponseEntity<ResponseObject> getAllProductUser() {
        List<ProductModel> kq = productService.getAllProductUser();
        List<ProductModel> check =  new ArrayList<ProductModel>();
        for (int i = 0; i < kq.size();i++){
            if(kq.get(i).getStatus() == 1 ){
                check.add(kq.get(i));
            }
        }
        return check.isEmpty() ?
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,"")
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                );
    }


    // GET by id: localhost:8080/api/v1/product/abc
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getProductById(@PathVariable("id") String id) {
        Optional<ProductModel> check = Optional.ofNullable(productService.getProductById(id));
        return check.isPresent() ?
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true, Error.OK_MESSAGE,check)
                ) :
                ResponseEntity.status(Error.NO_VALUE_BY_ID).body(
                        new ResponseObject(false, Error.NO_VALUE_BY_ID_MESSAGE,"")
                );
    }

    //GET : localhost:8080/api/v1/product/getProduct?cateId=MBA&Type=0
    // Type = 0 lay theo binh thuong, Type = 1 lay theo a->z , Type = 2 lay theo z->a , Type = 3 lay gia thap den cao , Type = 4 lay gia cao den thap
    @GetMapping("/getProduct")
    ResponseEntity<ResponseObject> getProductByCateID(@RequestParam(required = false) String cateId,@RequestParam(required = false) int Type) {
        List<ProductModel> check = productService.getProductByCateID(cateId,Type);
        return check.isEmpty() ?
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,"")
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                );
    }

    // POST : localhost:8080/api/v1/product/add         +    JSON(ProductModel)
    @PostMapping("/add")
    ResponseEntity<ResponseObject> addnewProduct(@RequestBody ProductModel productModel) {
        Optional<ProductModel> check = Optional.ofNullable(productService.getProductById(productModel.getproId()));
        if (check.isPresent() == true) {
            return ResponseEntity.status(Error.DUPLICATE_ID).body(
                    new ResponseObject(false,Error.DUPLICATE_ID_MESSAGE, "")
            );
        } else {
                List<ProductModel> list = productService.getAllProductUser();
            Long max = Long.valueOf(0);
            for (int i=0;i<list.size();i++){
                if(max < list.get(i).get_id()){
                    max = list.get(i).get_id();
                }
            }
            productModel.set_id(max+1);
            productService.saveProduct(productModel);
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE,"")
            );
        }
    }


    //Ko dung den
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

    // POST : localhost:8080/api/v1/product/statusHide?proId=abc
    @PostMapping("/statusHide")
    ResponseEntity<ResponseObject> statusHide(@RequestParam(required = false) String proId){
        ProductModel productModel = productService.getProductById(proId);
        Optional<ProductModel> check = Optional.ofNullable(productModel);
        if(check.isPresent() != true ){
            return ResponseEntity.status(Error.NO_VALUE_BY_ID).body(
                    new ResponseObject(false,Error.NO_VALUE_BY_ID_MESSAGE, "")
            );}
        else if(productModel.getStatus() == 0) {
            return ResponseEntity.status(Error.FAIL_STATUS_CHANGE).body(
                new ResponseObject(false, Error.FAIL_STATUS_CHANGE_MESSAGE,"")
                );}
        else {
            productService.statusHide(proId);
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE,"")
            );
        }
    }


    // POST : localhost:8080/api/v1/product/statusShow?proId=abc
    @PostMapping("/statusShow")
    ResponseEntity<ResponseObject> statusShow(@RequestParam(required = false) String proId){
        ProductModel productModel = productService.getProductById(proId);
        Optional<ProductModel> check = Optional.ofNullable(productModel);
        if(check.isPresent() != true ){
            return ResponseEntity.status(Error.NO_VALUE_BY_ID).body(
                    new ResponseObject(false,Error.NO_VALUE_BY_ID_MESSAGE, "")
            );}
        else if(productModel.getStatus() == 1) {
            return ResponseEntity.status(Error.FAIL_STATUS_CHANGE).body(
                    new ResponseObject(false, Error.FAIL_STATUS_CHANGE_MESSAGE,"")
            );}
        else {
            productService.statusShow(proId);
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE,"")
            );
        }
    }


    // POST : localhost:8080/api/v1/product/setPrice?proId=abc&price=100
    @PostMapping("/setPrice")
    ResponseEntity<ResponseObject> setPrice(@RequestParam(required = false) String proId,@RequestParam(required = false) Long price){
        ProductModel productModel = productService.getProductById(proId);
        Optional<ProductModel> check = Optional.ofNullable(productModel);
        if(check.isPresent() == true ){
            productService.updatePrice(proId,price);
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE,"")
            );}
        else {
            return ResponseEntity.status(Error.NO_VALUE_BY_ID).body(
                    new ResponseObject(false, Error.NO_VALUE_BY_ID_MESSAGE,"")
            );}
    }

    // POST : localhost:8080/api/v1/product/updateProduct?proId=abc        + JSON(ProductModel)  // ko thay doi dc proId + status
    @PostMapping("updateProduct")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody ProductModel newProduct, @RequestParam(required = false) String proId){
        ProductModel productModel = productService.getProductById(proId);
        Optional<ProductModel> check = Optional.ofNullable(productModel);
        if(check.isPresent() == true ){
            productService.updateProduct(newProduct,proId);
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE,"")
            );}
        else {
            return ResponseEntity.status(Error.NO_VALUE_BY_ID).body(
                    new ResponseObject(false, Error.NO_VALUE_BY_ID_MESSAGE,"")
            );}
    }

    // GET : localhost:8080/api/v1/product/searchAdmin?proName=abc
    @GetMapping("/searchAdmin")
    ResponseEntity<ResponseObject> searchProductAdmin(@RequestParam(required = false) String proName) {
        List<ProductModel> check = productService.searchProduct(proName);
        return check.isEmpty() ?
                ResponseEntity.status(Error.NO_VALUE_BY_ID).body(
                        new ResponseObject(false, Error.NO_VALUE_BY_ID_MESSAGE,"")
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                );
    }


    // GET : localhost:8080/api/v1/product/searchUser?proName=abc
    @GetMapping("/searchUser")
    ResponseEntity<ResponseObject> searchProductUser(@RequestParam(required = false) String proName) {
        List<ProductModel> kq = productService.searchProduct(proName);
        List<ProductModel> check =  new ArrayList<ProductModel>();
        for (int i = 0; i < kq.size();i++){
            if(kq.get(i).getStatus() == 1 ){
                check.add(kq.get(i));
            }
        }
        return check.isEmpty() ?
                ResponseEntity.status(Error.NO_VALUE_BY_ID).body(
                        new ResponseObject(false, Error.NO_VALUE_BY_ID_MESSAGE,"")
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                );
    }
}