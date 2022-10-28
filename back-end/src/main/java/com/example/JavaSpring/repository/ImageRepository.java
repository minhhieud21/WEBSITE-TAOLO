package com.example.JavaSpring.repository;

import com.example.JavaSpring.models.ImageModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends MongoRepository<ImageModel,String> {
    @Query("{'proID':?0 ,'status': ?1}") //SELECT * FROM Product WHERE ProID = ?0
    ImageModel getPathImageByID(String id,int status);
    @Query("{'proID':?0 ,'status': ?1}") //SELECT * FROM Product WHERE ProID = ?0
    List<ImageModel> getPathImage(String id,int status);
    @Query("{imgPath:/?0/}") //SELECT * FROM Product WHERE ProID = ?0
    List<ImageModel> getImagebyName(String imgPath);


}
