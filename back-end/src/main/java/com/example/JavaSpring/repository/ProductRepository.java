package com.example.JavaSpring.repository;

import com.example.JavaSpring.models.BlogModel;
import com.example.JavaSpring.models.CategoryModel;
import com.example.JavaSpring.models.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<ProductModel,String> {
    @Query("{proID:?0}") //SELECT * FROM Product WHERE ProID = ?0
    ProductModel getProductByID(String id);
}
