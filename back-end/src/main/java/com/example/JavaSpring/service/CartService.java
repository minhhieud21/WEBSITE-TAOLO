package com.example.JavaSpring.service;

import com.example.JavaSpring.models.CartModel;

import java.util.List;

public interface CartService {

    List<CartModel> getAllCart();

    CartModel getCartByID(String cartID);

    void saveCart(CartModel cartModel);

    void updateTotalQuantity(String cartID, int tQuantity);

    void updateTotalCost(String cartID,long tCost);

    void deleteCart(String cartID);
}
