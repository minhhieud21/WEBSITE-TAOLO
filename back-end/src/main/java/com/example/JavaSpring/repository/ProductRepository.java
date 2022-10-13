package com.example.JavaSpring.repository;

import com.example.JavaSpring.models.BlogModel;
import com.example.JavaSpring.models.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<ProductModel,String> {
    @Query("{proId:/?0/}") //SELECT * FROM Product WHERE ProID = ?0
    ProductModel getProductByID(String id);

    @Query("{cateId:/?0/}") //SELECT * FROM Product WHERE title like ?
    List<ProductModel> getProductByCateID(String cateId) ;

    @Query("{proName:/?0/}") //SELECT * FROM Product WHERE description like "?"
    List<ProductModel> getBlogByName(String proName) ;

}
