package com.example.JavaSpring.service;

import com.example.JavaSpring.models.BillDetailModel;

import java.util.List;

public interface BillDetailService {

    List<BillDetailModel> getAllBillDetail();

    BillDetailModel getBillDetailByID(String billDID);

    List<BillDetailModel> getBillDetailByBillID(String billID);

    void addBillDetail(BillDetailModel billDetailModel);
}
