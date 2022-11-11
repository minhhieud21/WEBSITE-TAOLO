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
    public List<BillModel> getBillByAccID(String accID){
        return  billRepository.getBillByAccID(accID);
    }

    @Override
    public void addBill(BillModel billModel){
        billRepository.save(billModel);
    }

    @Override
    public void updateBill(String billID, int quantity, long cost, int status){
        BillModel billModel = billRepository.getBillByBillID(billID);
        billModel.setStatus(status);
        billModel.setToTalQuantity(quantity);
        billModel.setTotalCost(cost);
        billRepository.save(billModel);
    }

    @Override
    public void deleteBill(String billID){
        BillModel billModel = billRepository.getBillByBillID(billID);
        billRepository.delete(billModel);
    }
}
