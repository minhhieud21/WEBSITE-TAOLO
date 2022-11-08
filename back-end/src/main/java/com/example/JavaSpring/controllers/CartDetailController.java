package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.CartDetailModel;
import com.example.JavaSpring.models.CartModel;
import com.example.JavaSpring.models.ProductModel;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.CartDetailService;
import com.example.JavaSpring.service.CartDetailServicelmpl;

import com.example.JavaSpring.service.CartServicelmpl;
import com.example.JavaSpring.service.ProductServiceImpl;
import com.example.JavaSpring.util.Error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/cartdetail") //localhost:8080/api/v1/cartdetail
@CrossOrigin(origins ="*")

public class CartDetailController {

    @Autowired
    public CartDetailController() {

    }

    @Autowired
    CartDetailService cartDetailService;

    @Autowired
    CartServicelmpl cartServicelmpl;

    @Autowired
    ProductServiceImpl productService;



    @GetMapping("")
    ResponseEntity<ResponseObject> getAllCartDetail() {
        List<CartDetailModel> check = cartDetailService.getAllCartDetail();
        return check.isEmpty() ?
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false,Error.LIST_EMPTY_MESSAGE, "")
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                );
    }


    @GetMapping("/{cartDID}")
    ResponseEntity<ResponseObject> getProductById(@PathVariable("cartDID") String cartDID) {
        Optional<CartDetailModel> check = Optional.ofNullable(cartDetailService.getCartDetailByID(cartDID));
        return check.isPresent() ?
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                ) :
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,"")
                );
    }

    // localhost:8080/api/v1/cartdetail/getCartDetailByCartID?cartID=?
//    @GetMapping("/getCartDetailByCartID")
//    ResponseEntity<ResponseObject> getCartDetailByCartID(String cartID) {
//        List<CartDetailModel> check = cartDetailService.getCartDetailByCartID(cartID);
//        return check.isEmpty() ?
//                ResponseEntity.status(Error.LIST_EMPTY).body(
//                        new ResponseObject(false, Error.LIST_EMPTY_MESSAGE, "")
//                ) :
//                ResponseEntity.status(Error.OK).body(
//                        new ResponseObject(true, Error.OK_MESSAGE, check)
//                );
//    }


//    @GetMapping("/{cartDID}")
//    ResponseEntity<ResponseObject> getCartDetailById(@PathVariable("cartDID") String cartDID) {
//        Optional<CartDetailModel> check = Optional.ofNullable(cartDetailService.getCartDetailByID(cartDID));
//        return check.isPresent() ?
//                ResponseEntity.status(Error.OK).body(
//                        new ResponseObject(true,Error.OK_MESSAGE, check)
//                ) :
//                ResponseEntity.status(Error.LIST_EMPTY).body(
//                        new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,"")
//                );
//    }


    CartDetailModel getCartDetailById(String cartDID) {
        return cartDetailService.getCartDetailByID(cartDID);
    }

    // localhost:8080/api/v1/cartdetail/getCartDetailByCartID?cartID=?
//    @GetMapping("/getCartDetailByCartID")
//    ResponseEntity<ResponseObject> getCartDetailByCartID(String cartID) {
//        List<CartDetailModel> check = cartDetailService.getCartDetailByCartID(cartID);
//        return check.isEmpty() ?
//                ResponseEntity.status(Error.LIST_EMPTY).body(
//                        new ResponseObject(false,Error.LIST_EMPTY_MESSAGE, "")
//                ) :
//                ResponseEntity.status(Error.OK).body(
//                        new ResponseObject(true,Error.OK_MESSAGE, check)
//                );
//    }
    List<CartDetailModel> getCartDetailByCartID(String cartID) {
        return cartDetailService.getCartDetailByCartID(cartID);
    }

    // localhost:8080/api/v1/cartdetail/getCartDetailByProID?proID=?
    @GetMapping("/getCartDetailByProID")
    ResponseEntity<ResponseObject> getCartDetailByProId(@RequestParam("cartID") String cartID,@RequestParam("proID") String proID) {
        CartDetailModel cartDetailModel = cartDetailService.getCartDetailByProID(cartID,proID);
        Optional<CartDetailModel> check = Optional.ofNullable(cartDetailModel);
        return check.isEmpty() ?
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false,Error.LIST_EMPTY_MESSAGE,"")
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE,"")
                );
    }
    CartDetailModel getCartDetailByProID(String cartID,String proID) {
        return cartDetailService.getCartDetailByProID(cartID,proID);
    }

    // POST localhost:8080/api/v1/cartdetail/addCartDetail
    @PostMapping("/addCartDetail")
    ResponseEntity<ResponseObject> addnewCartDetail(@RequestBody CartDetailModel cartDetailModel) {
        Optional<CartDetailModel> check = Optional.ofNullable(cartDetailService.getCartDetailByID(cartDetailModel.getCartDID()));
        if (check.isPresent() == true) {
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE,"")
            );
        } else {
            cartDetailService.saveCartDetail(cartDetailModel);
            return ResponseEntity.status(Error.LIST_EMPTY).body(
                    new ResponseObject(false,Error.LIST_EMPTY_MESSAGE,"")
            );
        }
    }

    // localhost:8080/api/v1/cartdetail/setQuantity?cartDID=?&quantity=?
//    @PostMapping("/setQuantity")
//    ResponseEntity<ResponseObject> setQuantity(String cartDID, int quantity) {
//        CartDetailModel cartDetailModel = cartDetailService.getCartDetailByID(cartDID);
//        Optional<CartDetailModel> check = Optional.ofNullable(cartDetailModel);
//        if(check.isPresent() == true && cartDetailModel.getQuantity() != quantity){
//            cartDetailService.updateQuantity(cartDID,quantity);
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject(true,"")

    String autoIDCartDetail(){
        List<CartDetailModel> cartDetailModelList = cartDetailService.getAllCartDetail();
        int nb = 0;
        for(int i = 0; i < cartDetailModelList.size(); i++){
            String CurID = cartDetailModelList.get(i).getCartDID();
            String[] ASCurID =  CurID.split("CD");
            int NCurID = Integer.parseInt(ASCurID[1]);
            if(NCurID > nb){
                nb = NCurID;
            }
        }
        String ck = "";
        int nbck = nb + 1;
        if(String.valueOf(nbck).length() == 1){
            ck = "CD00"+nbck;
        }else if(String.valueOf(nbck).length() == 2){
            ck = "CD0"+nbck;
        }else if(String.valueOf(nbck).length() == 3){
            ck = "CD" + nbck;
        }
        return ck;
    }

    //localhost:8080/api/v1/cartdetail/setQuantity?cartDID=?&quantity=?
    @PostMapping("/updateCartDetail")
    ResponseEntity<ResponseObject> updatecart(@RequestParam("cartDID") String cartDID,@RequestParam("quantity") int quantity){
        CartDetailModel cartDetailModelCur = cartDetailService.getCartDetailByID(cartDID);
        ProductModel productModelCur = productService.getProductById(cartDetailModelCur.getProID());
        long CostNew = productModelCur.getPrice() * quantity;
        cartDetailService.updateCartDetail(cartDID,quantity,CostNew);
        cartServicelmpl.updateCart(cartDetailModelCur.getCartID(),autoLoadQuantity(cartDetailModelCur.getCartID()),autoLoadQCost(cartDetailModelCur.getCartID()),0);
        CartDetailModel cartDetailModelNew = cartDetailService.getCartDetailByID(cartDID);
        Optional<CartDetailModel> check = Optional.ofNullable(cartDetailModelNew);
        if (check.isPresent() && cartDetailModelNew.getQuantity() == quantity && cartDetailModelNew.getCost() == CostNew){
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true, Error.OK_MESSAGE,"")
            );}
        else {
            return ResponseEntity.status(Error.LIST_EMPTY).body(
                    new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,"")
            );}
    }

    int updateCart(String cartDID, int quantity, long cost) {
        cartDetailService.updateCartDetail(cartDID,quantity,cost);
        CartDetailModel cartDetailModel = cartDetailService.getCartDetailByID(cartDID);
        if (cartDetailModel.getQuantity() == quantity && cartDetailModel.getCost() == cost) {
            return 1;
        } else {
            return 0;
        }
    }


    //DELETE localhost:8080/api/v1/cartdetail/deleteCartDetail/?
    @DeleteMapping("/deleteCartDetail/{cartDID}")
    ResponseEntity<ResponseObject> deleteCartDetail(@PathVariable("cartDID") String cartDID) {
        CartDetailModel cartDetailModel = cartDetailService.getCartDetailByID(cartDID);
        Optional<CartDetailModel> check = Optional.ofNullable(cartDetailModel);
        if(check.isPresent()){
            cartDetailService.deleteCartDetail(cartDID);
            int quantityNew = autoLoadQuantity(cartDetailModel.getCartID());
            long costNew = autoLoadQCost(cartDetailModel.getCartID());
            cartServicelmpl.updateCart(cartDetailModel.getCartID(),quantityNew,costNew,0);
            CartModel cartModel = cartServicelmpl.getCartByID(cartDetailModel.getCartID());
            CartDetailModel cartDetailModel1 = cartDetailService.getCartDetailByID(cartDID);
            Optional<CartDetailModel> check1 = Optional.ofNullable(cartDetailModel1);
            if(check1.isEmpty() && cartModel.getTotalQuantity() == quantityNew && cartModel.getTotalCost() == costNew){
                return ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE,"")
                );
            }else{
                return ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false,Error.LIST_EMPTY_MESSAGE, "")
                );
            }
        }else{
            return ResponseEntity.status(Error.LIST_EMPTY).body(
                    new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,"")
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
//                return ResponseEntity.status(Error.OK).body(
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
    //DELETE localhost:8080/api/v1/cartdetail/deleteCartDetail/
    @DeleteMapping("/deleteAllCartDetail/{cartID}")
    int deleteAllCartDetail (@PathVariable("cartID") String cartID) {
        List<CartDetailModel> check = cartDetailService.getCartDetailByCartID(cartID);

        if(!check.isEmpty()){
            cartDetailService.deleteAllCartDetail(cartID);
            List<CartDetailModel> check1 = cartDetailService.getCartDetailByCartID(cartID);
            if(check1.isEmpty()){
                return 1;
            }else{
                return 0;
            }
        }else{
            return 0;
        }
    }

    int autoLoadQuantity(String cartID){
        int kq = 0;
        List<CartDetailModel> cartDetailModelList = cartDetailService.getCartDetailByCartID(cartID);
        for(int i = 0; i < cartDetailModelList.size(); i++){
            kq += cartDetailModelList.get(i).getQuantity();
        }
        return kq;
    }

    long autoLoadQCost(String cartID){
        long kq = 0;
        List<CartDetailModel> cartDetailModelList = cartDetailService.getCartDetailByCartID(cartID);
        for(int i = 0; i < cartDetailModelList.size(); i++){
            kq += cartDetailModelList.get(i).getCost();
        }
        return kq;
    }
}
