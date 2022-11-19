package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.*;
import com.example.JavaSpring.service.*;
import com.example.JavaSpring.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/bill") //localhost:8080/api/v1/bill
@CrossOrigin(origins ="*")
public class BillController {
    @Autowired
    BillService billService;

    @Autowired
    BillDetailController billDetailController;

    @Autowired
    BillDetailServicelmpl billDetailServicel;

    @Autowired
    CartServicelmpl cartService;

    @Autowired
    CartDetailServicelmpl cartDetailServicel;

    @Autowired
    ProductServiceImpl productService;

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

    String autoIMEICode(@RequestParam("test") String proID){
        String[] splitID = proID.split("");
        splitID[8] = "_I";
        splitID[13] = "C_";
        splitID[18] = "_F";
        splitID[23] = "M_";
        String kq = "";
        for(int i = 0; i < splitID.length;i++){
            kq += splitID[i];
        }
        return kq;
    }

    // POST localhost:8080/api/v1/bill/addBillDetail
//    @PostMapping("/addBill")
//    ResponseEntity<ResponseObject> addBill(@RequestBody BillModel billModel) {
//        Optional<BillModel> check = Optional.ofNullable(billService.getBillByBillID(billModel.getBillID()));
//        if(check.isPresent()){
//            return ResponseEntity.status(Error.FAIL).body(
//                    new ResponseObject(false,Error.FAIL_MESSAGE, "")
//            );
//        }else{
//            billService.addBill(billModel);
//            Optional<BillModel> check1 = Optional.ofNullable(billService.getBillByBillID(billModel.getBillID()));
//            if(check1.isPresent()){
//                return ResponseEntity.status(Error.OK).body(
//                        new ResponseObject(true,Error.OK_MESSAGE, "")
//                );
//            }else{
//                return ResponseEntity.status(Error.FAIL).body(
//                        new ResponseObject(false,Error.FAIL_MESSAGE, "")
//                );
//            }
//        }
//    }
    @PostMapping("/addBill")
    ResponseEntity<ResponseObject> addBill(@RequestBody Map<String,String> value) {
        String cartID = value.get("cartID");
        CartModel CurCartModel = cartService.getCartByID(cartID);
        Optional<CartModel> checkE = Optional.ofNullable(CurCartModel);
        String ErrorMessage = "";
        int ck = 0;
        if(checkE.isPresent() && CurCartModel.getTotalQuantity() != 0){
            if(CurCartModel.getStatus() == 1){
                String billID = autoIDBill();
                List<CartDetailModel> cartDetailModelList = cartDetailServicel.getCartDetailByCartID(cartID);
                for (int i = 0; i < cartDetailModelList.size(); i++) {
                    String billDID = billDetailController.autoIDBillDetail();
                    String proID = cartDetailModelList.get(i).getProID();
                    long cost = cartDetailModelList.get(i).getCost();
                    int quantity = cartDetailModelList.get(i).getQuantity();
                    String imeiCode = autoIMEICode(cartDetailModelList.get(i).getProID());
                    ProductModel productModel = productService.getProductById(cartDetailModelList.get(i).getProID());
                    String date = date();
                    int wmonth = productModel.getWarrantyMonth();
                    BillDetailModel billDetailModel = new BillDetailModel(null,billDID,billID,proID,cost,quantity,imeiCode,date,warrantyDate(date,wmonth));
                    billDetailServicel.addBillDetail(billDetailModel);
                }
                Optional<List<BillDetailModel>> check1 = Optional.ofNullable(billDetailServicel.getBillDetailByBillID(billID));
                if(check1.isPresent()){
                    BillModel billModel = new BillModel(null,billID, billDetailServicel.autoLoadCost(billID), billDetailServicel.autoLoadQuantity(billID), CurCartModel.getAccID(),1, CurCartModel.getAddress(), CurCartModel.getMethodPay(), CurCartModel.getPhone(), CurCartModel.getDescription());
                    billService.addBill(billModel);
                    return ResponseEntity.status(Error.OK).body(
                            new ResponseObject(true,Error.OK_MESSAGE, "Complete")
                    );
                }else{
                    return ResponseEntity.status(Error.FAIL).body(
                            new ResponseObject(false,Error.FAIL_MESSAGE, "Error Bill Detail")
                    );
                }
            }else{
                return ResponseEntity.status(Error.FAIL).body(
                        new ResponseObject(false,Error.FAIL_MESSAGE, "Error Bill")
                );
            }
        }else{
            return ResponseEntity.status(Error.FAIL).body(
                    new ResponseObject(false,Error.FAIL_MESSAGE, "Cart Not Exist !!! Or There Is No Product On Cart !!!")
            );
        }
    }

    String date(){
        long millis=System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        return date.toString();
    }

    String warrantyDate(String date, int warrantyMonth){
        String[] dateSplit = date.split("-");
        int day = Integer.parseInt(dateSplit[2]);
        int month = Integer.parseInt(dateSplit[1]) + warrantyMonth;
        int months =  month / 12;
        int year = Integer.parseInt(dateSplit[0]);
        if(day == 29 && Integer.parseInt(dateSplit[1]) == 2){
            day = 1;
            month = 3 + warrantyMonth;
        }
        if(months != 0){
            year =  year + months;
            month = -((12*months) - month);
        }else if(months == 0){
            year =  year + months;
        }

        return String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);
    }

    @PostMapping("/changeStatusBill")
    ResponseEntity<ResponseObject> changeStatusBill(String billID, int status){
        Optional<BillModel> check = Optional.ofNullable(billService.getBillByBillID(billID));
        if(check.isPresent()){
            billService.changeStatusBill(billID,status);
            BillModel billModel = billService.getBillByBillID(billID);
            if(billModel.getStatus() == status){
                return ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, "Change Status Cart Success !!!")
                );
            }else{
                return ResponseEntity.status(Error.FAIL).body(
                        new ResponseObject(false,Error.FAIL_MESSAGE, "Change Status Cart Fail !!!")
                );
            }
        }else{
            return ResponseEntity.status(Error.FAIL).body(
                    new ResponseObject(false,Error.FAIL_MESSAGE, "Bill Not Exist !!!")
            );
        }
    }

    @DeleteMapping("/deleteBill")
    ResponseEntity<ResponseObject> deleteBill(String billID){
        BillModel billModel = billService.getBillByBillID(billID);
        Optional<BillModel> check = Optional.ofNullable(billModel);
        if(check.isPresent()){
            if(billModel.getStatus() == 0){
                billDetailServicel.deleteBillDetail(billID);
                billService.deleteBill(billID);
                Optional<BillModel> billModelCheck = Optional.ofNullable(billService.getBillByBillID(billID));
                if(billModelCheck.isEmpty()){
                    return ResponseEntity.status(Error.OK).body(
                            new ResponseObject(true,Error.OK_MESSAGE, "Delete Bill Success !!!")
                    );
                }else{
                    return ResponseEntity.status(Error.FAIL).body(
                            new ResponseObject(false,Error.FAIL_MESSAGE, "Delete Bill Fail !!!")
                    );
                }
            }else{
                return ResponseEntity.status(Error.FAIL).body(
                        new ResponseObject(false,Error.FAIL_MESSAGE, "Bill Not Error !!!")
                );
            }
        }else{
            return ResponseEntity.status(Error.FAIL).body(
                    new ResponseObject(false,Error.FAIL_MESSAGE, "Bill Not Exist !!!")
            );
        }
    }
}
