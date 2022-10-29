package com.example.JavaSpring.repository;

import com.example.JavaSpring.models.BillModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository  extends MongoRepository<BillModel,String> {

    @Query("{billID:?0}")
    BillModel getBillByBillID(String billID);

    @Query("{cusID:?0}")
    List<BillModel> getBillByCusID(String billID);
}
