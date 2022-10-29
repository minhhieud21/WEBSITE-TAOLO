package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.BillModel;
import com.example.JavaSpring.models.CartDetailModel;
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
    @GetMapping("getBillByCusID")
    ResponseEntity<ResponseObject> getBillByCusID(String cusID){
        List<BillModel> check = billService.getBillByCusID(cusID);
        return check.isEmpty() ?
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false,Error.LIST_EMPTY_MESSAGE, "")
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                );

    }

    // POST localhost:8080/api/v1/bill/addBill
//    @PostMapping("/addCartDetail")
//    ResponseEntity<ResponseObject> addnewBill(@RequestBody BillModel billModel) {
//        Optional<BillModel> check = Optional.ofNullable(billService.getBillByBillID(billModel.getBillID()));
//        if (check.isPresent() == true) {
//            return ResponseEntity.status(Error.LIST_EMPTY).body(
//                    new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,"")
//            );
//        } else {
//            cartDetailService.saveCartDetail(cartDetailModel);
//            return ResponseEntity.status(Error.OK).body(
//                    new ResponseObject(true,Error.OK_MESSAGE, "")
//            );
//        }
//    }
}
