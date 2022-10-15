package com.example.JavaSpring.repository;

import com.example.JavaSpring.models.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<ProductModel,String> {
    @Query("{proId:?0}") //SELECT * FROM Product WHERE ProID = ?0
    ProductModel getProductByID(String id);

    @Query("{cateId:/?0/}") //SELECT * FROM Product WHERE title like ?
    List<ProductModel> getProductByCateID(String cateId, Sort sort) ;

    @Query("{'proName': { '$regex' : ?0 , $options: 'i'}}")
    List<ProductModel> getBlogByName(String proName) ;

}
