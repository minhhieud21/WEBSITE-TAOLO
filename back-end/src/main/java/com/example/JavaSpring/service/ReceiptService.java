package com.example.JavaSpring.service;

import com.example.JavaSpring.models.ReceiptModel;

import java.util.List;

public interface ReceiptService {

    List<ReceiptModel> getAllReceipt();

    ReceiptModel getReceiptByID(String recID);

    String autoID();

    void changeStatusReceipt(String recID, int status);

    void deleteReceipt(String recID);

}
