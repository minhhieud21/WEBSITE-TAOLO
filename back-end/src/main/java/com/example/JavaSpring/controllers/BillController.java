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
    CartDetailController cartDetailController;

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

    // GET localhost:8080/api/v1/bill/getBillByBillID
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

    // GET localhost:8080/api/v1/bill/getBillByCusID
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

    // POST localhost:8080/api/v1/bill/addBill
    @PostMapping("/addBill")
    ResponseEntity<ResponseObject> addBill(@RequestBody Map<String,String> value) {
        String cartID = value.get("cartID");
        CartModel CurCartModel = cartService.getCartByID(cartID);
        Optional<CartModel> checkE = Optional.ofNullable(CurCartModel);
        String ErrorMessage = "";
        int ck = 0;
        if(checkE.isPresent()){
            if(CurCartModel.getStatus() == 1){
                String billID = autoIDBill();
                List<CartDetailModel> cartDetailModelList = cartDetailServicel.getCartDetailByCartID(cartID);
                for (int i = 0; i < cartDetailModelList.size(); i++) {
                    String cartDID = cartDetailModelList.get(i).getCartDID();
                    String billDID = billDetailController.autoIDBillDetail();
                    String proID = cartDetailModelList.get(i).getProID();
                    long cost = cartDetailModelList.get(i).getCost();
                    int quantity = cartDetailModelList.get(i).getQuantity();
                    String imeiCode = autoIMEICode(cartDetailModelList.get(i).getProID());
                    ProductModel productModel = productService.getProductById(proID);
                    String date = date();
                    int wmonth = productModel.getWarrantyMonth();
                    if(quantity <= productModel.getQuantity()){
                        BillDetailModel billDetailModel = new BillDetailModel(null,billDID,billID,proID,cost,quantity,imeiCode,date,warrantyDate(date,wmonth));
                        billDetailServicel.addBillDetail(billDetailModel);
                        int newQuantity = productModel.getQuantity() - quantity;
                        productService.updateQuantity(proID,newQuantity);
                        cartDetailServicel.deleteCartDetail(cartDID);
                        ProductModel productModelCheck = productService.getProductById(proID);
                        if(productModelCheck.getQuantity() == 0){
                            productService.statusHide(proID);
                        }
                    }else{
                        ErrorMessage = ErrorMessage + productModel.getProName() + ",";
                        CartModel cartModel = cartService.getCartByAccID(CurCartModel.getAccID());
                        Optional<CartModel> check = Optional.ofNullable(cartModel);
                        if(check.isPresent()){
                            String RecartDID = cartDetailController.autoIDCartDetail();
                            String RecartID = cartModel.getCartID();
                            CartDetailModel reCartDetailModel = new CartDetailModel(null,RecartDID,RecartID,proID,quantity,cost);
                            cartDetailServicel.saveCartDetail(reCartDetailModel);
                            cartDetailServicel.deleteCartDetail(cartDID);
                        }
                    }
                }
                List<CartDetailModel> cartDetailModelCheck = cartDetailServicel.getCartDetailByCartID(cartID);
                if(cartDetailModelCheck.isEmpty()){
                    cartService.deleteCart(cartID);
                }else{
                    cartService.updateCart(cartID,cartDetailController.autoLoadQuantity(cartID),cartDetailController.autoLoadQCost(cartID),0);
                }
                List<BillDetailModel> check = billDetailServicel.getBillDetailByBillID(billID);
                if(check.isEmpty()){
                    return ResponseEntity.status(Error.FAIL).body(
                            new ResponseObject(false,Error.FAIL_MESSAGE, "Those Product Is Not Enough Quantity:"+ErrorMessage)
                    );
                }else{
                    BillModel billModel = new BillModel(null,billID,CurCartModel.getTotalCost(),CurCartModel.getTotalQuantity(),CurCartModel.getAccID(),CurCartModel.getStatus(), CurCartModel.getName(),CurCartModel.getAddress(), CurCartModel.getMethodPay(),CurCartModel.getPhone(),CurCartModel.getDescription());
                    billService.addBill(billModel);
                    Optional<List<BillDetailModel>> check1 = Optional.ofNullable(billDetailServicel.getBillDetailByBillID(billID));
                    if(check1.isPresent()  && ErrorMessage == ""){
                        billService.updateBill(billID,billDetailController.autoLoadQuantity(billID), billDetailController.autoLoadCost(billID),1 );
                        return ResponseEntity.status(Error.OK).body(
                                new ResponseObject(true,Error.OK_MESSAGE, "Complete")
                        );
                    }else if(check1.isPresent() && ErrorMessage != ""){
                        billService.updateBill(billID,billDetailController.autoLoadQuantity(billID), billDetailController.autoLoadCost(billID),1 );
                        return ResponseEntity.status(Error.OK).body(
                                new ResponseObject(true,Error.OK_MESSAGE, "Those Product Is Not Enough Quantity:"+ErrorMessage)
                        );
                    }else{
                        return ResponseEntity.status(Error.FAIL).body(
                                new ResponseObject(false,Error.FAIL_MESSAGE, "Error Bill Detail")
                        );
                    }

                }
            }else{
                return ResponseEntity.status(Error.FAIL).body(
                        new ResponseObject(false,Error.FAIL_MESSAGE, "Error Bill")
                );
            }
        }else{
            return ResponseEntity.status(Error.FAIL).body(
                    new ResponseObject(false,Error.FAIL_MESSAGE, "Cart Not Exist !!!")
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

    // POST localhost:8080/api/v1/bill/chageStatusBill
    @PostMapping("/changeStatusBill")
    ResponseEntity<ResponseObject> chageStatusBill(@RequestParam("billID") String billID, @RequestParam("status") int status){
        Optional<BillModel> check = Optional.ofNullable(billService.getBillByBillID(billID));
        if(check.isPresent()){
            BillModel billModel = billService.getBillByBillID(billID);
            billService.updateBill(billID,billModel.getToTalQuantity(),billModel.getTotalCost(),status);
            BillModel billModelCheck = billService.getBillByBillID(billID);
            if(billModelCheck.getStatus() == status){
                return ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, "Chage Status Success !!!")
                );
            }else{
                return ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, "Chage Status Fail !!!")
                );
            }
        }else{
            return  ResponseEntity.status(Error.LIST_EMPTY).body(
                    new ResponseObject(false,Error.LIST_EMPTY_MESSAGE, "Bill Not Exist")
            );
        }
    }

    // DELETE localhost:8080/api/v1/bill/deleteBill
    @DeleteMapping("/deleteBill")
    ResponseEntity<ResponseObject> deleteBill(@RequestParam("billID") String billID){
        Optional<BillModel> check = Optional.ofNullable(billService.getBillByBillID(billID));
        if(check.isPresent()){
            BillModel billModel = billService.getBillByBillID(billID);
            if(billModel.getStatus() == 0){
                billService.deleteBill(billID);
                billDetailServicel.deleteBillDetailByBillID(billID);
                Optional<BillModel> billModelCheck = Optional.ofNullable(billService.getBillByBillID(billID));
                List<BillDetailModel> billDetailModelCheck = billDetailServicel.getBillDetailByBillID(billID);
                if(billModelCheck.isEmpty() && billDetailModelCheck.isEmpty()){
                    return  ResponseEntity.status(Error.OK).body(
                            new ResponseObject(true,Error.OK_MESSAGE, "Delete Success !!!")
                    );
                }else{
                    return  ResponseEntity.status(Error.FAIL).body(
                            new ResponseObject(false,Error.FAIL_MESSAGE, "Delete Fail !!!")
                    );
                }
            }else{
                return  ResponseEntity.status(Error.FAIL).body(
                        new ResponseObject(false,Error.FAIL_MESSAGE, "This Is Not Error Bill, Can Not Delete !!!")
                );
            }
        }else{
            return  ResponseEntity.status(Error.LIST_EMPTY).body(
                    new ResponseObject(false,Error.LIST_EMPTY_MESSAGE, "Bill Not Exist")
            );
        }
    }
}
