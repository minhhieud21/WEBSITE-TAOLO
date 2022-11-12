package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.CategoryModel;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.CategoryService;
import com.example.JavaSpring.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category") //localhost:8080/api/v1/category
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    // GET all : localhost:8080/api/v1/category/getAllCategoryAdmin
    @GetMapping("getAllCategoryAdmin")
    ResponseEntity<ResponseObject> getAllCategoryAdmin(){
        List<CategoryModel> check = categoryService.getAllCategory();
        return check.isEmpty() ?
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,"")
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                );
    }

    // GET all : localhost:8080/api/v1/category/getAllCategoryUser
    @GetMapping("getAllCategoryUser")
    ResponseEntity<ResponseObject> getAllCategoryUser() {
        List<CategoryModel> kq = categoryService.getAllCategory();
        List<CategoryModel> check =  new ArrayList<CategoryModel>();
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

    // GET by id: localhost:8080/api/v1/category/:id
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getCategoryById(@PathVariable("id") String id) {
        Optional<CategoryModel> check = Optional.ofNullable(categoryService.getCateByID(id));
        return check.isPresent() ?
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                ) :
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,"")
                );
    }

    // POST : localhost:8080/api/v1/category/add
    @PostMapping("/add")
    ResponseEntity<ResponseObject> addnewCategory(@RequestBody CategoryModel cateModel){
        Optional<CategoryModel> check = Optional.ofNullable(categoryService.getCateByID(cateModel.getCateID()));
        if (check.isPresent() == true) {
            return ResponseEntity.status(Error.LIST_EMPTY).body(
                    new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,"")
            );
        } else {
            categoryService.saveCate(cateModel);
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE, "")
            );
        }
    }

    @PostMapping("/setCateName")
    ResponseEntity<ResponseObject> setCateName(@RequestParam(required = false) String cateID,@RequestParam(required = false) String cateName){
        CategoryModel cateModel = categoryService.getCateByID(cateID);
        Optional<CategoryModel> check = Optional.ofNullable(cateModel);
        if(check.isPresent() == true && cateModel.getCateName() != cateName){
            categoryService.updateCateName(cateID,cateName);
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE,"")
            );}
        else {
            return ResponseEntity.status(Error.DUPLICATE_NAME).body(
                    new ResponseObject(false,Error.DUPLICATE_NAME_MESSAGE, "")
            );}
    }

    @PostMapping("/statusHide")
    ResponseEntity<ResponseObject> statusHide(@RequestParam(required = false) String cateID){
        CategoryModel cateModel = categoryService.getCateByID(cateID);
        Optional<CategoryModel> check = Optional.ofNullable(cateModel);
        if(check.isPresent() == true && cateModel.getStatus() != 0){
            categoryService.statusHide(cateID);
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE,"")
            );}
        else {
            return ResponseEntity.status(Error.FAIL_STATUS_CHANGE).body(
                    new ResponseObject(false,Error.FAIL_STATUS_CHANGE_MESSAGE, "")
            );}
    }
    @PostMapping("/statusShow")
    ResponseEntity<ResponseObject> statusShow(@RequestParam(required = false) String cateID){
        CategoryModel cateModel = categoryService.getCateByID(cateID);
        Optional<CategoryModel> check = Optional.ofNullable(cateModel);
        if(check.isPresent() == true && cateModel.getStatus() != 1){
            categoryService.statusShow(cateID);
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE,"")
            );}
        else {
            return ResponseEntity.status(Error.FAIL_STATUS_CHANGE).body(
                    new ResponseObject(false, Error.FAIL_STATUS_CHANGE_MESSAGE,"")
            );}
    }
}
