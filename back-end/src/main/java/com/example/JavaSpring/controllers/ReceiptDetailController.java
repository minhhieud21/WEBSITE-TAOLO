package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.ReceiptDetailModel;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.ReceiptDetailService;
import com.example.JavaSpring.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/receiptdetail") //localhost:8080/api/v1/receiptdetail
@CrossOrigin(origins = "*")
public class ReceiptDetailController {

    @Autowired
    ReceiptDetailService receiptDetailService;

    // GET : localhost:8080/api/v1/receiptdetail/getAllReceiptDetail
    @GetMapping("/getAllReceiptDetail")
    ResponseEntity<ResponseObject>  getAllReceiptDetail(){
        List<ReceiptDetailModel> check = receiptDetailService.getAllReceiptDetail();
        return check.isEmpty() ?
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,"")
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                );
    }

    // GET : localhost:8080/api/v1/receiptdetail/getReceiptDetailByID?recDID=RD001
    @GetMapping("/getReceiptDetailByID")
    ResponseEntity<ResponseObject>  getReceiptDetailByID(String recDID){
        Optional<ReceiptDetailModel> check = Optional.ofNullable(receiptDetailService.getReceiptDetailByID(recDID));
        return check.isEmpty() ?
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,"")
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                );
    }

    @PostMapping("/addReceiptDetail")
    ResponseEntity<ResponseObject> addReceiptDetail(@RequestBody ReceiptDetailModel receiptDetailModel){
        Optional<ReceiptDetailModel> check = Optional.ofNullable(receiptDetailService.getReceiptDetailByID(receiptDetailModel.getRecDID()));
        if(check.isEmpty()){
            receiptDetailService.addReceiptDetail(receiptDetailModel);
            Optional<ReceiptDetailModel> checkE = Optional.ofNullable(receiptDetailService.getReceiptDetailByID(receiptDetailModel.getRecDID()));
            if(checkE.isPresent()){
                return ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true, Error.OK_MESSAGE,"Receipt Detail Success !!!")
                );
            }else{
                return ResponseEntity.status(Error.FAIL).body(
                        new ResponseObject(false, Error.FAIL_MESSAGE,"Receipt Detail Fail !!!")
                );
            }
        }else{
            return ResponseEntity.status(Error.FAIL).body(
                    new ResponseObject(false, Error.FAIL_MESSAGE,"Receipt Detail Is Already Exist !!!")
            );
        }
    }
}
