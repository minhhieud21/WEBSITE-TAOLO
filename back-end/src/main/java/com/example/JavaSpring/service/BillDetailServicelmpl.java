package com.example.JavaSpring.service;

import com.example.JavaSpring.models.BillDetailModel;
import com.example.JavaSpring.repository.BillDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillDetailServicelmpl implements BillDetailService{

    @Autowired
    BillDetailRepository billDetailRepository;

    @Override
    public List<BillDetailModel> getAllBillDetail(){
        return billDetailRepository.findAll();
    }

    @Override
    public BillDetailModel getBillDetailByID(String billDID) {
        return billDetailRepository.getBillDetailByID(billDID);
    }

    @Override
    public List<BillDetailModel> getBillDetailByBillID(String billID) {
        return billDetailRepository.getBillDetailByBillID(billID);
    }

    @Override
    public  void addBillDetail(BillDetailModel billDetailModel){
        billDetailRepository.save(billDetailModel);
    }

    @Override
    public List<BillDetailModel> getBillDetailByDay(String day) {
        return billDetailRepository.getBillDetailByDay(day);
    }
}
