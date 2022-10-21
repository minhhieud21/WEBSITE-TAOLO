package com.example.JavaSpring.service;

import com.example.JavaSpring.models.CartDetailModel;

import java.util.List;

public interface CartDetailService {

    List<CartDetailModel> getAllCartDetail();

    CartDetailModel getCartDetailByID(String cartDID);

    List<CartDetailModel> getCartDetailByCartID(String cartID);

    List<CartDetailModel> getCartDetailByProID(String proID);

    void saveCartDetail(CartDetailModel cartDetailModel);

    void updateQuantity(String cartDID, int quantity);

    void deleteCartDetail(String cartDID);


}
