package com.example.JavaSpring.repository;


import com.example.JavaSpring.models.CartDetailModel;
import com.example.JavaSpring.models.CartModel;
import com.example.JavaSpring.service.CartDetailService;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDetailRepository extends MongoRepository<CartDetailModel,String> {
    @Query("{cartDID:/?0/}")
    CartDetailModel getCartDetailByID(String cartDID);

    @Query("{cartID:/?0/}")
    List<CartDetailModel> getCartDetailByCartID(String cartID);

    @Query("{cartID:/?0/,proID:/?1/}")
    CartDetailModel getCartDetailByProID(String cartID,String proID);
}
