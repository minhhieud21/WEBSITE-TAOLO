package com.example.JavaSpring.service;

import com.example.JavaSpring.models.ReceiptDetailModel;
import com.example.JavaSpring.models.ReceiptModel;

import java.util.List;

public interface ReceiptDetailService {

    List<ReceiptDetailModel> getAllReceiptDetail();

    ReceiptDetailModel getReceiptDetailByID(String recDID);

    List<ReceiptDetailModel> getReceiptDetailByRecID(String recID);

    void addReceiptDetail(ReceiptDetailModel receiptDetailModel);

    void deleteReceiptDetail(String recDID);

    void deleteAllReceiptDetail(String recID);

}
