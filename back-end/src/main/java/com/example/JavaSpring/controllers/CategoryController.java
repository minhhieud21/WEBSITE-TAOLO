package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.CategoryModel;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.CategoryService;
import com.example.JavaSpring.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
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

    // POST : localhost:8080/api/v1/category/addCategory
    @PostMapping("/addCategory")
    ResponseEntity<ResponseObject> addnewCategory(@RequestBody CategoryModel cateModel){
        Optional<CategoryModel> check = Optional.ofNullable(categoryService.getCateByID(cateModel.getCateID()));
        if (check.isPresent() == true) {
            return ResponseEntity.status(Error.FAIL).body(
                    new ResponseObject(false, Error.FAIL_MESSAGE,"Category Already Exist !!!")
            );
        } else {
            categoryService.saveCate(cateModel);
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE, "")
            );
        }
    }

    // POST : localhost:8080/api/v1/category/updateCategory
    @PostMapping("/updateCategory")
    ResponseEntity<ResponseObject> updateCategory(@RequestBody CategoryModel cateModel){
        Optional<CategoryModel> check = Optional.ofNullable(categoryService.getCateByID(cateModel.getCateID()));
        if (check.isPresent() == true) {
            if(cateModel.getCateID() != null && cateModel.getCateName() != null && String.valueOf(cateModel.getStatus()) != null){
                CategoryModel cate = categoryService.getCateByID(cateModel.getCateID());
                if(cateModel.getCateName() == ""){
                    categoryService.updateCategory(cateModel.getCateID(),cate.getCateName(),cateModel.getStatus());
                }else if(String.valueOf(cateModel.getStatus()) == ""){
                    categoryService.updateCategory(cateModel.getCateID(),cate.getCateName(),cate.getStatus());
                }else{
                    categoryService.updateCategory(cateModel.getCateID(),cateModel.getCateName(),cateModel.getStatus());
                }
                return ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true, Error.OK_MESSAGE,"Update Success !!!")
                );
            }else{
                return ResponseEntity.status(Error.FAIL).body(
                        new ResponseObject(false, Error.FAIL_MESSAGE,"Missing Data !!!")
                );
            }
        } else {
            return ResponseEntity.status(Error.FAIL).body(
                    new ResponseObject(false, Error.FAIL_MESSAGE,"Category Not Exist !!!")
            );
        }
    }

}
