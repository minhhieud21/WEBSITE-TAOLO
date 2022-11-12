package com.example.JavaSpring.service;

import com.example.JavaSpring.models.CartModel;

import java.util.List;

public interface CartService {



    List<CartModel> getAllCart();

    CartModel getCartByID(String cartID);

    CartModel getCartByAccID(String accID);

//    List<CartModel> getCartCheckOut(String accID);

    List<CartModel> getAllCartReadyCheckOut();

    void saveCart(CartModel cartModel);

    void updateCart(String cartID, int quantity, long cost, int status);

    void updateTotalQuantity(String cartID, int tQuantity);

    void updateTotalCost(String cartID,long tCost);

    void chageStatusCart(String cartI,int stt ,String name, String address, String methodPay, String phone, String description);

    void deleteCart(String cartID);


}
