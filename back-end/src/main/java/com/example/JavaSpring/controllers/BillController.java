package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.BillModel;
import com.example.JavaSpring.models.CartDetailModel;
import com.example.JavaSpring.models.CartModel;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.BillService;
import com.example.JavaSpring.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/bill") //localhost:8080/api/v1/bill
@CrossOrigin(origins ="*")
public class BillController {
    @Autowired
    BillService billService;

    // GET all : localhost:8080/api/v1/bill/
    @GetMapping("")
    ResponseEntity<ResponseObject> getAllBill(){
        List<BillModel> check = billService.getAllBill();
        return check.isEmpty() ?
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false,Error.LIST_EMPTY_MESSAGE, "")
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                );

    }

    // POST localhost:8080/api/v1/bill/getBillByBillID
    @GetMapping("getBillByBillID")
    ResponseEntity<ResponseObject> getBillByBillID(String billID){
        BillModel billModel = billService.getBillByBillID(billID);
        Optional<BillModel> check = Optional.ofNullable(billModel);
        return check.isEmpty() ?
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false,Error.LIST_EMPTY_MESSAGE, "")
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                );

    }

    // POST localhost:8080/api/v1/bill/getBillByCusID
    @GetMapping("getBillByAccID")
    ResponseEntity<ResponseObject> getBillByCusID(String accID){
        List<BillModel> check = billService.getBillByAccID(accID);
        return check.isEmpty() ?
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false,Error.LIST_EMPTY_MESSAGE, "")
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                );

    }
    String autoIDBill(){
        List<BillModel> billModelList = billService.getAllBill();
        int nb = 0;
        for(int i = 0; i < billModelList.size(); i++){
            String CurID = billModelList.get(i).getBillID();
            String[] ASCurID =  CurID.split("B");
            int NCurID = Integer.parseInt(ASCurID[1]);
            if(NCurID > nb){
                nb = NCurID;
            }
        }
        String ck = "";
        int nbck = nb + 1;
        if(String.valueOf(nbck).length() == 1){
            ck = "B00"+nbck;
        }else if(String.valueOf(nbck).length() == 2){
            ck = "B0"+nbck;
        }else if(String.valueOf(nbck).length() == 3){
            ck = "B" + nbck;
        }
        return ck;
    }

    // POST localhost:8080/api/v1/bill/addBillDetail
    @PostMapping("/addBill")
    ResponseEntity<ResponseObject> addBill(@RequestBody BillModel billModel) {
        Optional<BillModel> check = Optional.ofNullable(billService.getBillByBillID(billModel.getBillID()));
        if(check.isPresent()){
            return ResponseEntity.status(Error.FAIL).body(
                    new ResponseObject(false,Error.FAIL_MESSAGE, "")
            );
        }else{
            billService.addBill(billModel);
            Optional<BillModel> check1 = Optional.ofNullable(billService.getBillByBillID(billModel.getBillID()));
            if(check1.isPresent()){
                return ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, "")
                );
            }else{
                return ResponseEntity.status(Error.FAIL).body(
                        new ResponseObject(false,Error.FAIL_MESSAGE, "")
                );
            }
        }
    }
}
