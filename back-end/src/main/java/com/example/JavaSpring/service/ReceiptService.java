package com.example.JavaSpring.service;

import com.example.JavaSpring.models.ReceiptModel;

import java.util.List;

public interface ReceiptService {

    List<ReceiptModel> getAllReceipt();

    String autoID();
}
