package com.example.JavaSpring.service;

import com.example.JavaSpring.models.CartModel;
import com.example.JavaSpring.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServicelmpl implements CartService{

    @Autowired
    CartRepository cartRepository;

    @Override
    public List<CartModel> getAllCart() {
        return cartRepository.findAll();
    }

    @Override
    public CartModel getCartByID(String cartID){
        return cartRepository.getCartByID(cartID);
    }

    @Override
    public CartModel getCartByAccID(String accID){
        return cartRepository.getCartByAccID(accID);
    }

    @Override
    public void saveCart(CartModel cartModel){
        cartRepository.save(cartModel);
    }

    @Override
    public void updateTotalQuantity(String cartID, int tQuantity){
        CartModel cartModel = cartRepository.getCartByID(cartID);
        cartModel.setTotalQuantity(tQuantity);
        cartRepository.save(cartModel);
    }

    @Override
    public void updateTotalCost(String cartID, long tCost){
        CartModel cartModel = cartRepository.getCartByID(cartID);
        cartModel.setTotalCost(tCost);
        cartRepository.save(cartModel);
    }

    @Override
    public void updateCart(String cartID, int quantity, long cost){
        CartModel cartModel = cartRepository.getCartByID(cartID);
        cartModel.setTotalQuantity(quantity);
        cartModel.setTotalCost(cost);
        cartRepository.save(cartModel);
    }

    @Override
    public void deleteCart(String cartID){
        CartModel cartModel = cartRepository.getCartByID(cartID);
        cartRepository.delete(cartModel);
    }


}
