package com.example.JavaSpring.service;

import com.example.JavaSpring.models.ReceiptDetailModel;

import java.util.List;

public interface ReceiptDetailService {

    List<ReceiptDetailModel> getAllReceiptDetail();

    ReceiptDetailModel getReceiptDetailByID(String recDID);

    void addReceiptDetail(ReceiptDetailModel receiptDetailModel);

//    String autoID();
}
