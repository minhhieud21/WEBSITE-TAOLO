package com.example.JavaSpring.repository;

import com.example.JavaSpring.models.BillDetailModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailRepository extends MongoRepository<BillDetailModel,String> {

    @Query("{billDID:?0}")
    BillDetailModel getBillDetailByID(String billDID);

    @Query("{billID:?0}")
    List<BillDetailModel> getBillDetailByBillID(String billID);

    @Query("{warrantyStart:?0}")
    List<BillDetailModel> getBillDetailByDay(String day);

    @Query("{warrantyStart:/?0/}")
    List<BillDetailModel> getBillDetailByMonth(String day);
}
