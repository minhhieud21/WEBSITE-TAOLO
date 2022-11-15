package com.example.JavaSpring.repository;

import com.example.JavaSpring.models.ReceiptDetailModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptDetailRepository extends MongoRepository<ReceiptDetailModel, String> {

    @Query("{recDID:/?0/}")
    ReceiptDetailModel getReceiptDetailByID(String recDID);
}
