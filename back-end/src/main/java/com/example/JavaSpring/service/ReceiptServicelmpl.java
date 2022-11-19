package com.example.JavaSpring.service;

import com.example.JavaSpring.models.ReceiptDetailModel;
import com.example.JavaSpring.models.ReceiptModel;
import com.example.JavaSpring.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptServicelmpl implements ReceiptService{

    @Autowired
    ReceiptRepository receiptRepository;

    @Override
    public List<ReceiptModel> getAllReceipt(){
        return receiptRepository.findAll();
    }

    @Override
    public String autoID(){
        List<ReceiptModel> receiptModelList = receiptRepository.findAll();
        int nb = 0;
        for(int i = 0; i < receiptModelList.size(); i++){
            String CurID = receiptModelList.get(i).getRecID();
            String[] ASCurID =  CurID.split("R");
            int NCurID = Integer.parseInt(ASCurID[1]);
            if(NCurID > nb){
                nb = NCurID;
            }
        }
        String ck = "";
        int nbck = nb + 1;
        if(String.valueOf(nbck).length() == 1){
            ck = "R00"+nbck;
        }else if(String.valueOf(nbck).length() == 2){
            ck = "R0"+nbck;
        }else if(String.valueOf(nbck).length() == 3){
            ck = "R" + nbck;
        }
        return ck;
    }

    @Override
    public ReceiptModel getReceiptByID(String recID){
        return receiptRepository.getReceiptByID(recID);
    }

    @Override
    public void addReceipt(ReceiptModel receiptModel){
        receiptRepository.save(receiptModel);
    }

    @Override
    public void changeStatusReceipt(String recID, int status){
        ReceiptModel receiptModel = receiptRepository.getReceiptByID(recID);
        receiptModel.setStatus(status);
        receiptRepository.save(receiptModel);
    }

    @Override
    public void deleteReceipt(String recID){
        ReceiptModel receiptModel = receiptRepository.getReceiptByID(recID);
        receiptRepository.delete(receiptModel);
    }

}
