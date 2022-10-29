package com.example.JavaSpring.service;

import com.example.JavaSpring.models.CartDetailModel;

import java.util.List;

public interface CartDetailService {

    List<CartDetailModel> getAllCartDetail();

    CartDetailModel getCartDetailByID(String cartDID);

    List<CartDetailModel> getCartDetailByCartID(String cartID);

    CartDetailModel getCartDetailByProID(String cartID,String proID);

    void saveCartDetail(CartDetailModel cartDetailModel);

    void updateCartDetail(String cartDID, int quantity, long cost);

    void deleteCartDetail(String cartDID);

    void deleteAllCartDetail(String cartID);

}
