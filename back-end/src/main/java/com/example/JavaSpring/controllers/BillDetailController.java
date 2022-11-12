package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.BillDetailModel;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.BillDetailService;
import com.example.JavaSpring.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/billDetail") //localhost:8080/api/v1/product
@CrossOrigin(origins ="*")
public class BillDetailController {

    @Autowired
    BillDetailService billDetailService;

    // GET all : localhost:8080/api/v1/billDetail/
    @GetMapping("")
    ResponseEntity<ResponseObject> getAllBillDetail(){
        Optional<List<BillDetailModel>> check = Optional.ofNullable(billDetailService.getAllBillDetail());
        return check.isEmpty() ?
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false,Error.LIST_EMPTY_MESSAGE, "")
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                );

    }

    @GetMapping("/getBillDetailByID")
    ResponseEntity<ResponseObject> getBillDetailByID(String billDID){
        Optional<BillDetailModel> check = Optional.ofNullable(billDetailService.getBillDetailByID(billDID));
        return check.isEmpty() ?
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false,Error.LIST_EMPTY_MESSAGE, "")
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                );

    }

    @GetMapping("/getBillDetailByBillID")
    ResponseEntity<ResponseObject> getBillDetailByBillID(String billID){
        Optional<List<BillDetailModel>> check = Optional.ofNullable(billDetailService.getBillDetailByBillID(billID));
        return check.isEmpty() ?
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false,Error.LIST_EMPTY_MESSAGE, "")
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                );

    }

    String autoIDBillDetail(){
        List<BillDetailModel> billDetailModelList = billDetailService.getAllBillDetail();
        int nb = 0;
        for(int i = 0; i < billDetailModelList.size(); i++){
            String CurID = billDetailModelList.get(i).getBillDID();
            String[] ASCurID =  CurID.split("BD");
            int NCurID = Integer.parseInt(ASCurID[1]);
            if(NCurID > nb){
                nb = NCurID;
            }
        }
        String ck = "";
        int nbck = nb + 1;
        if(String.valueOf(nbck).length() == 1){
            ck = "BD00"+nbck;
        }else if(String.valueOf(nbck).length() == 2){
            ck = "BD0"+nbck;
        }else if(String.valueOf(nbck).length() == 3){
            ck = "BD" + nbck;
        }
        return ck;
    }

    @PostMapping("/addBillDetail")
    ResponseEntity<ResponseObject> addBillDetail(@RequestBody BillDetailModel billDetailModel) {
        Optional<BillDetailModel> check = Optional.ofNullable(billDetailService.getBillDetailByID(billDetailModel.getBillDID()));
        if(check.isPresent()){
            return ResponseEntity.status(Error.FAIL).body(
                    new ResponseObject(false,Error.FAIL_MESSAGE, "")
            );
        }else{
            billDetailService.addBillDetail(billDetailModel);
            Optional<BillDetailModel> check1 = Optional.ofNullable(billDetailService.getBillDetailByID(billDetailModel.getBillDID()));
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
