package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.CartDetailModel;
import com.example.JavaSpring.models.CartModel;
import com.example.JavaSpring.models.ProductModel;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.CartService;
import com.example.JavaSpring.service.ProductServiceImpl;
import com.example.JavaSpring.util.Error;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/cart") //localhost:8080/api/v1/product
@CrossOrigin(origins ="*")

public class CartController {

    public CartController() {
    }

    @Autowired
    CartService cartService;
    @Autowired
    CartDetailController cartDetailController = new CartDetailController();
    @Autowired
    ProductServiceImpl productService = new ProductServiceImpl();



    // GET all : localhost:8080/api/v1/cart
    @GetMapping("")
    ResponseEntity<ResponseObject> getAllCart() {
        List<CartModel> check = cartService.getAllCart();
        return check.isEmpty() ?
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false,Error.LIST_EMPTY_MESSAGE, check)
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                );
    }

    // GET by id: localhost:8080/api/v1/cart/:id
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getCartById(@PathVariable("id") String cartID) {
        Optional<CartModel> check = Optional.ofNullable(cartService.getCartByID(cartID));
        return check.isEmpty() ?
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false,Error.LIST_EMPTY_MESSAGE, "")
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                );
    }
    CartModel getCartByCartID(String cartID) {
       return cartService.getCartByID(cartID);
    }

    // GET by id: localhost:8080/api/v1/cart/getCartByAccID?
    @GetMapping("/getCartByAccID")
    ResponseEntity<ResponseObject> getCartByAccID( String accID) {
        Optional<CartModel> check = Optional.ofNullable(cartService.getCartByAccID(accID));
        return check.isEmpty() ?
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false,Error.LIST_EMPTY_MESSAGE, "")
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                );
    }

    // GET by id: localhost:8080/api/v1/cart/getAllCartReadyCheckOut
    @GetMapping("/getAllCartReadyCheckOut")
    ResponseEntity<ResponseObject> getAllCartReadyCheckOut() {
        List<CartModel> check = cartService.getAllCartReadyCheckOut();
        return check.isEmpty() ?
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false,Error.LIST_EMPTY_MESSAGE, "")
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                );
    }

    // POST localhost:8080/api/v1/cart/addCart
    @PostMapping("/addCart")
    ResponseEntity<ResponseObject> addnewCart(@RequestBody Map<String,String> value) {
        int ck = 0;
        String accID = value.get("accID");
        String proID = value.get("proID");
        int quantity = Integer.parseInt(value.get("quantity"));
        String cartID = autoIDCart();
        String cartDID = cartDetailController.autoIDCartDetail();
        ProductModel CurPro = productService.getProductById(proID);
        Optional<CartModel> check = Optional.ofNullable(cartService.getCartByAccID(accID));
        long PriceNew = CurPro.getPrice() * quantity;
        if(check.isEmpty()){
            if(quantity <= CurPro.getQuantity()){
                CartModel cartModelNew = new CartModel(null,cartID,accID,quantity,PriceNew,0,"","","","","");
                CartDetailModel cartDetailModelNew = new CartDetailModel(null,cartDID,cartID,proID,quantity,PriceNew);
                cartService.saveCart(cartModelNew);
                cartDetailController.addnewCartDetail(cartDetailModelNew);
                Optional<CartModel> check1 = Optional.ofNullable(cartService.getCartByID(cartID));
                Optional<CartDetailModel> check2 = Optional.ofNullable(cartDetailController.getCartDetailById(cartDID));
                if (check1.isPresent() == true && check2.isPresent() == true) {
                    ck = 1;
                }
            }else{
                return ResponseEntity.status(Error.FAIL).body(
                        new ResponseObject(false, Error.FAIL_MESSAGE,"Product Quantity Error !!!")
                );
            }
        }else{
            CartModel CurCart = cartService.getCartByAccID(accID);
            if(CurCart.getStatus() != 2){
                Optional<CartDetailModel> check1 = Optional.ofNullable(cartDetailController.getCartDetailByProID(CurCart.getCartID(), proID));
                if (check1.isPresent()) {
                    CartDetailModel CurCartDetail = cartDetailController.getCartDetailByProID(CurCart.getCartID(), proID);
                    int QuantityNew = CurCartDetail.getQuantity() + quantity;
                    long CostNew = CurPro.getPrice() * QuantityNew;
                    if(QuantityNew <= CurPro.getQuantity()){
                        ck = cartDetailController.updateCart(CurCartDetail.getCartDID(), QuantityNew, CostNew);
                    }else{
                        return ResponseEntity.status(Error.FAIL).body(
                                new ResponseObject(false, Error.FAIL_MESSAGE,"Product Quantity Error !!!")
                        );
                    }
                } else {
                    if(quantity <= CurPro.getQuantity()){
                        CartDetailModel CartDetailNew = new CartDetailModel(null, cartDID, CurCart.getCartID(), proID, quantity, PriceNew);
                        cartDetailController.addnewCartDetail(CartDetailNew);
                        Optional<CartDetailModel> check2 = Optional.ofNullable(cartDetailController.getCartDetailById(CartDetailNew.getCartDID()));
                        if (check2.isPresent()) {
                            ck = 1;
                        }
                    }else{
                        return ResponseEntity.status(Error.FAIL).body(
                                new ResponseObject(false, Error.FAIL_MESSAGE,"Product Quantity Error !!!")
                        );
                    }
                }
                updateCart(CurCart.getCartID(), cartDetailController.autoLoadQuantity(CurCart.getCartID()), cartDetailController.autoLoadQCost(CurCart.getCartID()),0);
            }else{
                ck = 0;
            }
        }
        if (ck == 1) {
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true, Error.OK_MESSAGE, "")
            );
        } else {
            return ResponseEntity.status(Error.FAIL).body(
                    new ResponseObject(false, Error.FAIL_MESSAGE,"")
            );
        }
    }


    String autoIDCart(){
        List<CartModel> cartModelList = cartService.getAllCart();
        int nb = 0;
        for(int i = 0; i < cartModelList.size(); i++){
            String CurID = cartModelList.get(i).getCartID();
            String[] ASCurID =  CurID.split("C");
            int NCurID = Integer.parseInt(ASCurID[1]);
            if(NCurID > nb){
                nb = NCurID;
            }
        }
        String ck = "";
        int nbck = nb + 1;
        if(String.valueOf(nbck).length() == 1){
            ck = "C00"+nbck;
        }else if(String.valueOf(nbck).length() == 2){
            ck = "C0"+nbck;
        }else if(String.valueOf(nbck).length() == 3){
            ck = "C" + nbck;
        }
        return ck;
    }

    @PostMapping("/updateCart")
    ResponseEntity<ResponseObject> updateCart(@RequestParam String cartID,@RequestParam(required = false) int quantity,@RequestParam(required = false) long cost, @RequestParam(required = false) int status){
        cartService.updateCart(cartID,quantity,cost,status);
        CartModel cartModel = cartService.getCartByID(cartID);
        Optional<CartModel> check = Optional.ofNullable(cartModel);
        if(check.isPresent() == true && cartModel.getTotalQuantity() == quantity && cartModel.getTotalCost() == cost){
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE,"")
            );}
        else {
            return ResponseEntity.status(Error.FAIL).body(
                    new ResponseObject(false, Error.FAIL_MESSAGE,"")
            );}
    }

    @DeleteMapping("/delete/{cartID}")
    ResponseEntity<ResponseObject> deleteCart(@PathVariable("cartID") String cartID){
        CartModel cartModel = cartService.getCartByID(cartID);
        Optional<CartModel> check = Optional.ofNullable(cartModel);

        if(check.isPresent()){
            cartService.deleteCart(cartID);
            CartModel cartModel1 = cartService.getCartByID(cartID);
            Optional<CartModel> check1 = Optional.ofNullable(cartModel1);
            int check2 = cartDetailController.deleteAllCartDetail(cartID);
            if(check1.isEmpty() && check2 == 1){
                return ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE,"")
                );
            }else{
                return ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false,Error.LIST_EMPTY_MESSAGE, "")
                );
            }
        }else{
            return ResponseEntity.status(Error.FAIL).body(
                    new ResponseObject(false,Error.FAIL_MESSAGE,"")
            );
        }
    }

    //methodPay: cash or momo
    @PostMapping("/readyCheckout")
    ResponseEntity<ResponseObject> readyCheckout(@RequestBody Map<String,String> value){
        String cartID = value.get("cartID");
        String name = value.get("name");
        String address = value.get("address");
        String methodPay = value.get("methodPay");
        String phone = value.get("phone");
        String description = value.get("description");
        CartModel cartModel = cartService.getCartByID(cartID);
        Optional<CartModel> check = Optional.ofNullable(cartModel);
        if(check.isPresent()){
            if(address != null  && methodPay != null && name != null && phone != null){
                if(address != ""  && methodPay != "" && name != "" && phone != ""){
                    cartService.chageStatusCart(cartID,1,name,address,methodPay,phone,description);
                    CartModel check1 = cartService.getCartByID(cartID);
                    if(check1.getStatus() == 1){
                        return ResponseEntity.status(Error.OK).body(
                                new ResponseObject(true,Error.OK_MESSAGE,"")
                        );
                    }else{
                        return ResponseEntity.status(Error.FAIL).body(
                                new ResponseObject(false,Error.FAIL_MESSAGE,"Chage Status Fail !!!")
                        );
                    }
                }else{
                    if(cartModel.getAddress().isEmpty() && cartModel.getMethodPay().isEmpty() && cartModel.getName().isEmpty() && cartModel.getPhone().isEmpty()){
                        return ResponseEntity.status(Error.FAIL).body(
                                new ResponseObject(false,Error.FAIL_MESSAGE,"Missing Data !!!")
                        );
                    }else{
                        cartService.chageStatusCart(cartID,1,cartModel.getName(),cartModel.getAddress(),cartModel.getMethodPay(),cartModel.getPhone(),cartModel.getDescription());
                        CartModel check1 = cartService.getCartByID(cartID);
                        if(check1.getStatus() == 1){
                            return ResponseEntity.status(Error.OK).body(
                                    new ResponseObject(true,Error.OK_MESSAGE,"")
                            );
                        }else{
                            return ResponseEntity.status(Error.FAIL).body(
                                    new ResponseObject(false,Error.FAIL_MESSAGE,"Chage Status Fail !!!")
                            );
                        }
                    }
                }
            }else{
                    return ResponseEntity.status(Error.FAIL).body(
                            new ResponseObject(false,Error.FAIL_MESSAGE,"Missing Data !!!")
                    );
            }
        }else{
            return ResponseEntity.status(Error.FAIL).body(
                    new ResponseObject(false,Error.FAIL_MESSAGE,"Cart Not Exist !!!")
            );
        }
    }

}
