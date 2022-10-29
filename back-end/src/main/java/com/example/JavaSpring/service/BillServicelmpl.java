package com.example.JavaSpring.service;

import com.example.JavaSpring.models.BillModel;
import com.example.JavaSpring.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServicelmpl implements BillService{
    @Autowired
    BillRepository billRepository;

    @Override
    public List<BillModel> getAllBill(){
        return billRepository.findAll();
    }

    @Override
    public BillModel getBillByBillID(String billID){
        return  billRepository.getBillByBillID(billID);
    }

    @Override
    public List<BillModel> getBillByCusID(String cusID){
        return  billRepository.getBillByCusID(cusID);
    }

    @Override
    public void addBill(BillModel billModel){
        billRepository.save(billModel);
    }
}
