package com.example.JavaSpring.service;

import com.example.JavaSpring.models.BillModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BillService {

    List<BillModel> getAllBill();

    BillModel getBillByBillID(String billID);

    List<BillModel> getBillByCusID(String billID);

    void addBill(BillModel billModel);

}
