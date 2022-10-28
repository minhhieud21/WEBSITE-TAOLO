package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.CartDetailModel;
import com.example.JavaSpring.models.CartModel;
import com.example.JavaSpring.models.ProductModel;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.CartService;
<<<<<<< Updated upstream
=======
import com.example.JavaSpring.service.CartDetailService;
import com.example.JavaSpring.util.Error;
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/cart") //localhost:8080/api/v1/product
@CrossOrigin(origins ="http://localhost:4200")

public class CartController {

    public CartController() {
    }

    @Autowired
    CartService cartService;
    @Autowired
    CartDetailController cartDetailController = new CartDetailController();
    @Autowired
    ProductController productController = new ProductController();



    // GET all : localhost:8080/api/v1/cart
    @GetMapping("")
    ResponseEntity<ResponseObject> getAllCart() {
        List<CartModel> check = cartService.getAllCart();
        return check.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(false, "")
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(true, check)
                );
    }

    // GET by id: localhost:8080/api/v1/cart/:id
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getCartById(@PathVariable("id") String cartID) {
        Optional<CartModel> check = Optional.ofNullable(cartService.getCartByID(cartID));
<<<<<<< Updated upstream
        return check.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(true, check)
=======
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
>>>>>>> Stashed changes
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(false, "")
                );
    }

    // POST localhost:8080/api/v1/cart/addCart
    @PostMapping("/addCart")
<<<<<<< Updated upstream
    ResponseEntity<ResponseObject> addnewCart(@RequestBody CartModel cartModel) {
        Optional<CartModel> check = Optional.ofNullable(cartService.getCartByID(cartModel.getCartID()));
        if (check.isPresent() == true) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(false, "")
            );
        } else {
            cartService.saveCart(cartModel);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(true, "")
=======
//    ProductModel test(@RequestParam("accID") String accID, @RequestParam("quantity") int quantity, @RequestParam("proID") String proID){
//        return productController.getProductByProID(proID);
//    }
    ResponseEntity<ResponseObject> addnewCart(@RequestParam("accID") String accID, @RequestParam("quantity") int quantity, @RequestParam("proID") String proID) {

        int ck = 0;
        String cartID = autoIDCart();
        String cartDID = cartDetailController.autoIDCartDetail();
        ProductModel CurPro = productController.getProductByProID(proID);
        Optional<CartModel> check = Optional.ofNullable(cartService.getCartByAccID(accID));
        long PriceNew = CurPro.getPrice() * quantity;
        if(check.isEmpty()){
            CartModel cartModelNew = new CartModel(null,cartID,accID,quantity,PriceNew);
            CartDetailModel cartDetailModelNew = new CartDetailModel(null,cartDID,cartID,proID,quantity,PriceNew);
            cartService.saveCart(cartModelNew);
            cartDetailController.addnewCartDetail(cartDetailModelNew);
            Optional<CartModel> check1 = Optional.ofNullable(cartService.getCartByID(cartID));
            Optional<CartDetailModel> check2 = Optional.ofNullable(cartDetailController.getCartDetailById(cartDID));
            if (check1.isPresent() == true && check2.isPresent() == true) {
                ck = 1;
            }
        }else{
            CartModel CurCart = cartService.getCartByAccID(accID);
            Optional<CartDetailModel> check1 = Optional.ofNullable(cartDetailController.getCartDetailByProID(CurCart.getCartID(),proID));
            if(check1.isPresent()){
                CartDetailModel CurCartDetail = cartDetailController.getCartDetailByProID(CurCart.getCartID(),proID);
                int QuantityNew = CurCartDetail.getQuantity() + quantity;
                long CostNew = CurPro.getPrice() * QuantityNew;
                ck = cartDetailController.updateCart(CurCartDetail.getCartDID(),QuantityNew,CostNew);
            }else{
                CartDetailModel CartDetailNew = new CartDetailModel(null,cartDID,CurCart.getCartID(),proID,quantity,PriceNew);
                cartDetailController.addnewCartDetail(CartDetailNew);
                Optional<CartDetailModel> check2 = Optional.ofNullable(cartDetailController.getCartDetailById(CartDetailNew.getCartDID()));
                if(check2.isPresent()){
                    ck = 1;
                }
            }
            updateCart(CurCart.getCartID(),cartDetailController.autoLoadQuantity(CurCart.getCartID()),cartDetailController.autoLoadQCost(CurCart.getCartID()));
        }
        if (ck == 1) {
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true, Error.OK_MESSAGE, "")
            );
        } else {
            return ResponseEntity.status(Error.FAIL).body(
                    new ResponseObject(false, Error.FAIL_MESSAGE,"")
>>>>>>> Stashed changes
            );
        }
    }


    String autoIDCart(){
        List<CartModel> cartModelList = cartService.getAllCart();
        int nb = 0;
        for(int i = 0; i < cartModelList.size(); i++){
            String CurID = cartModelList.get(i).getCartID();
            String[] ASCurID =  CurID.split("00");
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
    ResponseEntity<ResponseObject> updateCart(@RequestParam("cartID") String cartID,@RequestParam("quantity") int quantity,@RequestParam("cost") long cost){
        cartService.updateCart(cartID,quantity,cost);
        CartModel cartModel = cartService.getCartByID(cartID);
        Optional<CartModel> check = Optional.ofNullable(cartModel);
        if(check.isPresent() == true && cartModel.getTotalQuantity() == quantity && cartModel.getTotalCost() == cost){

            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE,"")
            );}
        else {
            return ResponseEntity.status(Error.LIST_EMPTY).body(
                    new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,"")
            );}
    }

    @PostMapping("/setTotalQuantity")
    ResponseEntity<ResponseObject> setCateName(@RequestParam(required = false) String cartID,@RequestParam(required = false) int tQuantity){
        CartModel cartModel = cartService.getCartByID(cartID);
        Optional<CartModel> check = Optional.ofNullable(cartModel);
        if(check.isPresent() == true && cartModel.getTotalQuantity() != tQuantity){
            cartService.updateTotalQuantity(cartID, tQuantity);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(true,"")
            );}
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(false, "")
            );}
    }

    @PostMapping("/setTotalCost")
    ResponseEntity<ResponseObject> setCateName(@RequestParam(required = false) String cartID,@RequestParam(required = false) long tCost){
        CartModel cartModel = cartService.getCartByID(cartID);
        Optional<CartModel> check = Optional.ofNullable(cartModel);
        if(check.isPresent() == true && cartModel.getTotalCost() != tCost){
            cartService.updateTotalCost(cartID, tCost);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(true,"")
            );}
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(false, "")
            );}
    }

    //
    @DeleteMapping("/delete/{cartID}")
    ResponseEntity<ResponseObject> deleteCart(@PathVariable("cartID") String cartID){
        CartModel cartModel = cartService.getCartByID(cartID);
        Optional<CartModel> check = Optional.ofNullable(cartModel);
        if(check.isPresent()){
            cartService.deleteCart(cartID);
            CartModel cartModel1 = cartService.getCartByID(cartID);
            Optional<CartModel> check1 = Optional.ofNullable(cartModel1);
<<<<<<< Updated upstream
            if(check1.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(true,"")
=======
            int check2 = cartDetailController.deleteAllCartDetail(cartID);
            if(check1.isEmpty() && check2 == 1){
                return ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE,"")
>>>>>>> Stashed changes
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


//    int deleteAllCartDetail(String cartID) {
//        List<CartDetailModel> check = cartDetailService.getCartDetailByCartID(cartID);
//        if(!check.isEmpty()){
//            List<CartDetailModel> cartDetailModelList = cartDetailService.getCartDetailByCartID(cartID);
//            for(int i = 0;i < cartDetailModelList.size();i++){
//                cartDetailService.deleteCartDetail(cartDetailModelList.get(i).getCartDID());
//            }
//            List<CartDetailModel> check1 = cartDetailService.getCartDetailByCartID(cartID);
//            if(check1.isEmpty()){
//                return 1;
//            }else{
//                return 0;
//            }
//        }else{
//            return 0;
//        }
//    }

}
