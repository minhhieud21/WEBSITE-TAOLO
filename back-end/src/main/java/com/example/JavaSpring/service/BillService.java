package com.example.JavaSpring.service;

import com.example.JavaSpring.models.BillModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BillService {

    List<BillModel> getAllBill();

    BillModel getBillByBillID(String billID);

    List<BillModel> getBillByAccID(String accID);

    void addBill(BillModel billModel);

    void updateBill(String billID, int quantity, long cost, int status);

    void deleteBill(String billID);
}
