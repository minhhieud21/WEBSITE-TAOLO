package com.example.JavaSpring.service;

import com.example.JavaSpring.models.CartModel;

import java.util.List;

public interface CartService {


    List<CartModel> getAllCart();

    List<CartModel> getAllCartReadyCheckOut();

    List<CartModel> getCartReadyCheckOutByAccID(String accID);
    CartModel getCartByID(String cartID);

    CartModel getCartByAccID(String accID);

    void saveCart(CartModel cartModel);

    void updateCart(String cartID, int quantity, long cost, int status);

    void updateTotalQuantity(String cartID, int tQuantity);

    void updateTotalCost(String cartID,long tCost);

    void chageStatusCart(String cartID,int stt, String address, String methodPay, String phone, String description);

    void deleteCart(String cartID);


}
