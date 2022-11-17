package com.example.JavaSpring.repository;

import com.example.JavaSpring.models.ReceiptModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends MongoRepository<ReceiptModel,String> {

    @Query("{recID:/?0/}")
    ReceiptModel getReceiptByID(String recID);
}
