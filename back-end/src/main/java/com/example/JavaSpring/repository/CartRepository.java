package com.example.JavaSpring.repository;

import com.example.JavaSpring.models.CartModel;
import com.example.JavaSpring.models.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends MongoRepository<CartModel,String> {

    @Query("{cartID:/?0/}")
    CartModel getCartByID(String cartID);

    @Query("{accID:/?0/,status:0}")
    CartModel getCartByAccID(String accID);

    @Query("{accID:/?0/,status:2}")
    List<CartModel> getCartCheckOut(String accID);

}
