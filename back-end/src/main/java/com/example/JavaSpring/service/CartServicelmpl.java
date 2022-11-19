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
    public List<CartModel> getAllCartReadyCheckOut(){
        return cartRepository.getAllCartReadyCheckOut();
    }

    @Override
    public List<CartModel> getCartReadyCheckOutByAccID(String accID){
        return cartRepository.getCartReadyCheckOutByAccID(accID);
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
    public void chageStatusCart(String cartID,int stt, String name,String address, String methodPay, String phone, String description){
        CartModel cartModel = cartRepository.getCartByID(cartID);
        if(cartModel.getStatus() != stt ){
            cartModel.setStatus(stt);
        }
        if(cartModel.getAddress() != address){
            cartModel.setAddress(address);
        }
        if(cartModel.getMethodPay() != methodPay){
            cartModel.setMethodPay(methodPay);
        }
        if(cartModel.getPhone() != phone){
            cartModel.setPhone(phone);
        }
        if(cartModel.getDescription() != description){
            cartModel.setDescription(description);
        }
        if(cartModel.getName() != name){
            cartModel.setName(name);
        }
        cartRepository.save(cartModel);
    }

    @Override
    public void deleteCart(String cartID){
        CartModel cartModel = cartRepository.getCartByID(cartID);
        cartRepository.delete(cartModel);
    }


}
