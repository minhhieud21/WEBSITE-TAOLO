package com.example.JavaSpring.repository;

import com.example.JavaSpring.models.BillModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  BillRepository  extends MongoRepository<BillModel,String> {

    @Query("{billID:?0}")
    BillModel getBillByBillID(String billID);

    @Query("{accID:?0}")
    List<BillModel> getBillByAccID(String accID);

    @Query("{billID:?0,status:?1}")
    BillModel getBillByDayDone(String BillID,int status);
}
