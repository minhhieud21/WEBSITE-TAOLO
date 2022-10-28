package com.example.JavaSpring.service;

import com.example.JavaSpring.models.CartDetailModel;
import com.example.JavaSpring.repository.CartDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartDetailServicelmpl implements CartDetailService{

    @Autowired
    CartDetailRepository cartDetailRepository;

    @Override
    public List<CartDetailModel> getAllCartDetail() {
        return cartDetailRepository.findAll();
    }

    @Override
    public CartDetailModel getCartDetailByID(String cartDID){
        return cartDetailRepository.getCartDetailByID(cartDID);
    }

    @Override
    public List<CartDetailModel> getCartDetailByCartID(String cartID){
        return cartDetailRepository.getCartDetailByCartID(cartID);
    }

    @Override
    public CartDetailModel getCartDetailByProID(String cartID,String proID){
        return cartDetailRepository.getCartDetailByProID(cartID,proID);
    }

    @Override
    public void saveCartDetail(CartDetailModel cartDetailModel){
        cartDetailRepository.save(cartDetailModel);
    }

    @Override
    public void updateCartDetail(String cartDID, int quantity, long cost){
        CartDetailModel cartDetailModel = cartDetailRepository.getCartDetailByID(cartDID);
        cartDetailModel.setQuantity(quantity);
        cartDetailModel.setCost(cost);
        cartDetailRepository.save(cartDetailModel);
    }

    @Override
    public void deleteCartDetail(String cartDID){
        CartDetailModel cartDetailModel = cartDetailRepository.getCartDetailByID(cartDID);
        cartDetailRepository.delete(cartDetailModel);
    }

    @Override
    public void deleteAllCartDetail(String cartID){
        List<CartDetailModel> cartDetailModel = cartDetailRepository.getCartDetailByCartID(cartID);
        cartDetailRepository.deleteAll(cartDetailModel);
    }

}
