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
    public List<CartModel> getCartCheckOut(String accID){
        return cartRepository.getCartCheckOut(accID);
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
    public void updateCart(String cartID, int quantity, long cost, int status){
        CartModel cartModel = cartRepository.getCartByID(cartID);
        if(cartModel.getStatus() != status){
            cartModel.setStatus(status);
        }
        if(cartModel.getTotalCost() != cost){
            cartModel.setTotalCost(cost);
        }
        if(cartModel.getTotalQuantity() != quantity){
            cartModel.setTotalQuantity(quantity);
        }
        cartRepository.save(cartModel);
    }

    @Override
    public void chageStatusCart(String cartID,int stt, String address, String methodPay){
        CartModel cartModel = cartRepository.getCartByID(cartID);
        cartModel.setStatus(stt);
        cartModel.setAddress(address);
        cartModel.setMethodPay(methodPay);
        cartRepository.save(cartModel);
    }

    @Override
    public void deleteCart(String cartID){
        CartModel cartModel = cartRepository.getCartByID(cartID);
        cartRepository.delete(cartModel);
    }


}
