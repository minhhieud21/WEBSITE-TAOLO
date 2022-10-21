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

//    // GET all : localhost:8080/api/v1/cart
//    @GetMapping("")
//    ResponseEntity<ResponseObject> getAllCart() {
//        List<CartModel> check = cartService.getAllCart();
//        return check.isEmpty() ?
//                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                        new ResponseObject(false, "")
//                ) :
//                ResponseEntity.status(HttpStatus.OK).body(
//                        new ResponseObject(true, check)
//                );
//    }
//
//    // GET by id: localhost:8080/api/v1/cart/:id
//    @GetMapping("/{id}")
//    ResponseEntity<ResponseObject> getCartById(@PathVariable("id") String cartID) {
//        Optional<CartModel> check = Optional.ofNullable(cartService.getCartByID(cartID));
//        return check.isPresent() ?
//                ResponseEntity.status(HttpStatus.OK).body(
//                        new ResponseObject(true, check)
//                ) :
//                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                        new ResponseObject(false, "")
//                );
//    }
//
//    // POST localhost:8080/api/v1/cart/addCart
//    @PostMapping("/addCart")
//    ResponseEntity<ResponseObject> addnewCart(@RequestBody CartModel cartModel) {
//        Optional<CartModel> check = Optional.ofNullable(cartService.getCartByID(cartModel.getCartID()));
//        if (check.isPresent() == true) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject(false, "")
//            );
//        } else {
//            cartService.saveCart(cartModel);
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject(true, "")
//            );
//        }
//    }
//
//    @PostMapping("/setTotalQuantity")
//    ResponseEntity<ResponseObject> setCateName(@RequestParam(required = false) String cartID,@RequestParam(required = false) int tQuantity){
//        CartModel cartModel = cartService.getCartByID(cartID);
//        Optional<CartModel> check = Optional.ofNullable(cartModel);
//        if(check.isPresent() == true && cartModel.getTotalQuantity() != tQuantity){
//            cartService.updateTotalQuantity(cartID, tQuantity);
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject(true,"")
//            );}
//        else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject(false, "")
//            );}
//    }
//
//    @PostMapping("/setTotalCost")
//    ResponseEntity<ResponseObject> setCateName(@RequestParam(required = false) String cartID,@RequestParam(required = false) long tCost){
//        CartModel cartModel = cartService.getCartByID(cartID);
//        Optional<CartModel> check = Optional.ofNullable(cartModel);
//        if(check.isPresent() == true && cartModel.getTotalCost() != tCost){
//            cartService.updateTotalCost(cartID, tCost);
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject(true,"")
//            );}
//        else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject(false, "")
//            );}
//    }
//
//    @DeleteMapping("/delete/{cartID}")
//    ResponseEntity<ResponseObject> deleteCart(@PathVariable("cartID") String cartID){
//        CartModel cartModel = cartService.getCartByID(cartID);
//        Optional<CartModel> check = Optional.ofNullable(cartModel);
//
//        if(check.isPresent()){
//            cartService.deleteCart(cartID);
//            CartModel cartModel1 = cartService.getCartByID(cartID);
//            Optional<CartModel> check1 = Optional.ofNullable(cartModel1);
//            if(check1.isEmpty()){
//                return ResponseEntity.status(HttpStatus.OK).body(
//                        new ResponseObject(true,"")
//                );
//            }else{
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                        new ResponseObject(false, "")
//                );
//            }
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject(false, "")
//            );
//        }
//    }


}
