package com.example.JavaSpring.service;

import com.example.JavaSpring.controllers.ReceiptDetailController;
import com.example.JavaSpring.models.CartModel;
import com.example.JavaSpring.models.ReceiptDetailModel;
import com.example.JavaSpring.repository.ReceiptDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptDetailServicelmpl implements ReceiptDetailService{

    @Autowired
    ReceiptDetailRepository receiptDetailRepository;

    @Override
    public List<ReceiptDetailModel> getAllReceiptDetail(){
        return receiptDetailRepository.findAll();
    }

    @Override
    public ReceiptDetailModel getReceiptDetailByID(String recDID){
        return receiptDetailRepository.getReceiptDetailByID(recDID);
    }

    @Override
    public List<ReceiptDetailModel> getReceiptDetailByRecID(String recID){
        return receiptDetailRepository.getReceiptDetailByRecID(recID);
    }

    @Override
    public void addReceiptDetail(ReceiptDetailModel receiptDetailModel){
        receiptDetailRepository.save(receiptDetailModel);
    }

    @Override
    public void deleteReceiptDetail(String recDID){
        ReceiptDetailModel receiptDetailModel = receiptDetailRepository.getReceiptDetailByID(recDID);
        receiptDetailRepository.delete(receiptDetailModel);
    }

    @Override
    public void deleteAllReceiptDetail(String recID){
        List<ReceiptDetailModel> receiptDetailModelList = receiptDetailRepository.getReceiptDetailByRecID(recID);
        receiptDetailRepository.deleteAll(receiptDetailModelList);
    }

    public String autoID(){
        List<ReceiptDetailModel> receiptDetailModelList = receiptDetailRepository.findAll();
        int nb = 0;
        for(int i = 0; i < receiptDetailModelList.size(); i++){
            String CurID = receiptDetailModelList.get(i).getRecDID();
            String[] ASCurID =  CurID.split("RD");
            int NCurID = Integer.parseInt(ASCurID[1]);
            if(NCurID > nb){
                nb = NCurID;
            }
        }
        String ck = "";
        int nbck = nb + 1;
        if(String.valueOf(nbck).length() == 1){
            ck = "RD00"+nbck;
        }else if(String.valueOf(nbck).length() == 2){
            ck = "RD0"+nbck;
        }else if(String.valueOf(nbck).length() == 3){
            ck = "RD" + nbck;
        }
        return ck;
    }
}
