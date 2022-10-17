package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.CartDetailModel;
import com.example.JavaSpring.models.CartModel;
import com.example.JavaSpring.models.ProductModel;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.CartDetailService;
import com.example.JavaSpring.service.CartDetailServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/cartdetail") //localhost:8080/api/v1/cartdetail
@CrossOrigin(origins ="http://localhost:4200")

public class CartDetailController {

    @Autowired
    CartDetailService cartDetailService;

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllCartDetail() {
        List<CartDetailModel> check = cartDetailService.getAllCartDetail();
        return check.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(false, "")
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(true, check)
                );
    }

    @GetMapping("/{cartDID}")
    ResponseEntity<ResponseObject> getProductById(@PathVariable("cartDID") String cartDID) {
        Optional<CartDetailModel> check = Optional.ofNullable(cartDetailService.getCartDetailByID(cartDID));
        return check.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(true, check)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(false, "")
                );
    }

    // localhost:8080/api/v1/cartdetail/getCartDetailByCartID?cartID=?
    @GetMapping("/getCartDetailByCartID")
    ResponseEntity<ResponseObject> getCartDetailByCartID(String cartID) {
        List<CartDetailModel> check = cartDetailService.getCartDetailByCartID(cartID);
        return check.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(false, "")
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(true, check)
                );
    }

    // localhost:8080/api/v1/cartdetail/getCartDetailByProID?proID=?
    @GetMapping("/getCartDetailByProID")
    ResponseEntity<ResponseObject> getCartDetailByProID(String proID) {
        List<CartDetailModel> check = cartDetailService.getCartDetailByProID(proID);
        return check.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(false, "")
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(true, check)
                );
    }

    // POST localhost:8080/api/v1/cartdetail/addCartDetail
    @PostMapping("/addCartDetail")
    ResponseEntity<ResponseObject> addnewCartDetail(@RequestBody CartDetailModel cartDetailModel) {
        Optional<CartDetailModel> check = Optional.ofNullable(cartDetailService.getCartDetailByID(cartDetailModel.getCartDID()));
        if (check.isPresent() == true) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(false, "")
            );
        } else {
            cartDetailService.saveCartDetail(cartDetailModel);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(true, "")
            );
        }
    }

    // localhost:8080/api/v1/cartdetail/setQuantity?cartDID=?&quantity=?
    @PostMapping("/setQuantity")
    ResponseEntity<ResponseObject> setQuantity(String cartDID, int quantity) {
        CartDetailModel cartDetailModel = cartDetailService.getCartDetailByID(cartDID);
        Optional<CartDetailModel> check = Optional.ofNullable(cartDetailModel);
        if(check.isPresent() == true && cartDetailModel.getQuantity() != quantity){
            cartDetailService.updateQuantity(cartDID,quantity);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(true,"")
            );}
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(false, "")
            );}
    }

    //DELETE localhost:8080/api/v1/cartdetail/deleteCartDetail/?
    @DeleteMapping("/deleteCartDetail/{cartDID}")
    ResponseEntity<ResponseObject> deleteCartDetail(@PathVariable("cartDID") String cartDID) {
        CartDetailModel cartDetailModel = cartDetailService.getCartDetailByID(cartDID);
        Optional<CartDetailModel> check = Optional.ofNullable(cartDetailModel);
        if(check.isPresent()){
            cartDetailService.deleteCartDetail(cartDID);
            CartDetailModel cartDetailModel1 = cartDetailService.getCartDetailByID(cartDID);
            Optional<CartDetailModel> check1 = Optional.ofNullable(cartDetailModel1);
            if(check1.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(true,"")
                );
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(false, "")
                );
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(false, "")
            );
        }
    }

//    //DELETE localhost:8080/api/v1/cartdetail/deleteCartDetail/?
//    @DeleteMapping("/deleteCartDetail/{cartDID}")
//    ResponseEntity<ResponseObject> deleteCartDetail(@PathVariable("cartDID") String cartDID) {
//        CartDetailModel cartDetailModel = cartDetailService.getCartDetailByID(cartDID);
//        Optional<CartDetailModel> check = Optional.ofNullable(cartDetailModel);
//        if(check.isPresent()){
//            cartDetailService.deleteCartDetail(cartDID);
//            CartDetailModel cartDetailModel1 = cartDetailService.getCartDetailByID(cartDID);
//            Optional<CartDetailModel> check1 = Optional.ofNullable(cartDetailModel1);
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
