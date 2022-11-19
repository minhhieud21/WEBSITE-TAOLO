package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.*;
import com.example.JavaSpring.service.CategoryServiceImpl;
import com.example.JavaSpring.service.ProductServiceImpl;
import com.example.JavaSpring.service.ReceiptDetailServicelmpl;
import com.example.JavaSpring.service.ReceiptService;
import com.example.JavaSpring.util.Error;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping("/api/v1/receipt") //localhost:8080/api/v1/receipt
@CrossOrigin(origins = "*")
public class ReceiptController {

    @Autowired
    ReceiptService receiptService;

    @Autowired
    ReceiptDetailServicelmpl receiptDetailService;

    @Autowired
    CategoryServiceImpl categoryService;

    @Autowired
    ProductController productController;

    @Autowired
    ProductServiceImpl productService;

    // Get : localhost:8080/api/v1/receipt/getAllReceipt
    @GetMapping("/getAllReceipt")
    ResponseEntity<ResponseObject>  getAllReceipt(){
        List<ReceiptModel> check = receiptService.getAllReceipt();
        return check.isEmpty() ?
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,"")
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                );
    }

    @GetMapping("/getReceiptByID")
    ResponseEntity<ResponseObject>  getReceiptByID(String recID){
        Optional<ReceiptModel> check = Optional.ofNullable(receiptService.getReceiptByID(recID));
        return check.isEmpty() ?
                ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,"")
                ) :
                ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check)
                );
    }

    @PostMapping("/changeReceiptStatus")
    ResponseEntity<ResponseObject> changeReceiptStatus(String recID, int status){
        Optional<ReceiptModel> check = Optional.ofNullable(receiptService.getReceiptByID(recID));
        if(check.isPresent()){
            receiptService.changeStatusReceipt(recID,status);
            ReceiptModel receiptModel = receiptService.getReceiptByID(recID);
            if(receiptModel.getStatus() == status){
                return ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true, Error.OK_MESSAGE,"Change Status Receipt Success !!!")
                );
            }else{
                return ResponseEntity.status(Error.FAIL).body(
                        new ResponseObject(false, Error.FAIL_MESSAGE,"Change Status Receipt Fail !!!")
                );
            }
        }else{
            return ResponseEntity.status(Error.FAIL).body(
                    new ResponseObject(false, Error.FAIL_MESSAGE,"Receipt Not Exist !!!")
            );
        }

    }

    String date(){
        long millis=System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        return date.toString();
    }

    @PostMapping("/addReceipt")
    ResponseEntity<ResponseObject> addReceipt(@RequestParam("proID") String[] proID,@RequestParam("proName") String[] proName, @RequestParam("description") String[] description, @RequestParam("price") long[] price, @RequestParam("cost") long[] cost, @RequestParam("totalCost") long[] totalCost,@RequestParam("cateID") String[] cateID, @RequestParam("color") String[] color, @RequestParam("quantity") int[] quantity, @RequestParam("warrantyMonth") int[] warrantyMonth,@RequestParam("file") MultipartFile[] listFile) throws IOException {
        int ck = 0;
        if(proID.length == 0 || proName.length == 0 || description.length == 0 || quantity.length == 0 || price.length == 0 || cost.length == 0 || totalCost.length == 0 || cateID.length == 0 || color.length == 0 || warrantyMonth.length == 0 || listFile.length == 0){
            return ResponseEntity.status(Error.FAIL).body(
                        new ResponseObject(false, Error.FAIL_MESSAGE,"Missing Data !!!")
                );
        }else{
            if(proID.length == proName.length  || proName.length == description.length || quantity.length == description.length || price.length == quantity.length || cost.length == price.length || totalCost.length == cost.length || cateID.length == totalCost.length || color.length == cateID.length ||  warrantyMonth.length == color.length || listFile.length == proID.length){
                for(int i = 0; i < cateID.length ; i++){
                        Optional<CategoryModel> check = Optional.ofNullable(categoryService.getCateByID(cateID[i]));
                        if(check.isEmpty()) {
                            return ResponseEntity.status(Error.FAIL).body(
                                    new ResponseObject(false, Error.FAIL_MESSAGE, "Some Category Is Not Exist!!!")
                            );
                        }
                }
                int totalQuantity = 0;
                long costTotal = 0;
                productController.addListProduct(proID,proName,description,price,cateID,color,quantity,warrantyMonth,listFile);
                String recID = receiptService.autoID();
                for(int i = 0 ; i < proID.length; i++){
                    Optional<ProductModel> check1 = Optional.ofNullable(productService.getProductById(proID[i]));
                    String recDID = receiptDetailService.autoID();
                    if(check1.isPresent()){
                        ReceiptDetailModel newReceiptDetail = new ReceiptDetailModel(null,recDID,recID,proID[i],proName[i],description[i],quantity[i],price[i],cost[i],totalCost[i],warrantyMonth[i]);
                        receiptDetailService.addReceiptDetail(newReceiptDetail);
                        totalQuantity = totalQuantity + quantity[i];
                        costTotal = costTotal + totalCost[i];
                    }
                }
                List<ReceiptDetailModel> check2 = receiptDetailService.getReceiptDetailByRecID(recID);
                if(!check2.isEmpty()){
                    ReceiptModel newReceipt = new ReceiptModel(null,recID,date(),totalQuantity,costTotal,1);
                    receiptService.addReceipt(newReceipt);
                }
                List<ReceiptDetailModel> check3 = receiptDetailService.getReceiptDetailByRecID(recID);
                Optional<ReceiptModel> check4 = Optional.ofNullable(receiptService.getReceiptByID(recID));
                if(!check3.isEmpty() && check4.isPresent()){
                    return ResponseEntity.status(Error.OK).body(
                            new ResponseObject(true, Error.OK_MESSAGE,"Add Receipt Success !!!")
                    );
                }else{
                    return ResponseEntity.status(Error.FAIL).body(
                            new ResponseObject(false, Error.FAIL_MESSAGE,"Add Receipt Fail !!!")
                    );
                }
            }else{
                return ResponseEntity.status(Error.FAIL).body(
                        new ResponseObject(false, Error.FAIL_MESSAGE,"Missing Data !!!")
                );
            }
        }
    }

    @PostMapping("/showReceiptExcelFile")
    ResponseEntity<ResponseObject> showReceiptExcelFile(@RequestParam("list") MultipartFile[] list) throws IOException{
        if(list.length == 1 && list[0].getOriginalFilename().equals("") == true ) {
            return ResponseEntity.status(Error.LIST_EMPTY).body(
                    new ResponseObject(false,Error.LIST_EMPTY_MESSAGE,"")
            );
        }else{
            XSSFWorkbook workbook = new XSSFWorkbook(list[0].getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);
            List<Map<String,String>> NewProList = new ArrayList<>();
            for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
                XSSFRow row = worksheet.getRow(i);
                Map<String,String> newPro = new TreeMap<>();
                newPro.put("proID",row.getCell(1).getStringCellValue());
                newPro.put("proName",row.getCell(2).getStringCellValue());
                newPro.put("description",row.getCell(3).getStringCellValue());
                newPro.put("price",String.valueOf((int)row.getCell(4).getNumericCellValue()));
                newPro.put("cost",String.valueOf((int)row.getCell(5).getNumericCellValue()));
                newPro.put("totalCost",String.valueOf((int)row.getCell(6).getNumericCellValue()));
                newPro.put("cateID",row.getCell(7).getStringCellValue());
                newPro.put("color",row.getCell(8).getStringCellValue());
                newPro.put("quantity",String.valueOf((int) row.getCell(9).getNumericCellValue()));
                newPro.put("warrantyMonth",String.valueOf((int) row.getCell(10).getNumericCellValue()));
                NewProList.add(newPro);
            }
            if(NewProList.size() > 0){
                return ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, NewProList)
                );
            }else{
                return ResponseEntity.status(Error.FAIL).body(
                        new ResponseObject(false,Error.FAIL_MESSAGE, "Can Not read Data In File !!!")
                );
            }
        }
    }

    @DeleteMapping("/deleteReceipt")
    ResponseEntity<ResponseObject> deleteReceipt(String recID){
        ReceiptModel receiptModel = receiptService.getReceiptByID(recID);
        Optional<ReceiptModel> check = Optional.ofNullable(receiptModel);
        List<ReceiptDetailModel> check1 = receiptDetailService.getReceiptDetailByRecID(recID);
            if(check.isPresent() && !check1.isEmpty()){
                if(receiptModel.getStatus() == 0){
                    receiptService.deleteReceipt(recID);
                    receiptDetailService.deleteAllReceiptDetail(recID);
                    Optional<ReceiptModel> check2 = Optional.ofNullable(receiptService.getReceiptByID(recID));
                    List<ReceiptDetailModel> check3 = receiptDetailService.getReceiptDetailByRecID(recID);
                    if(check2.isEmpty() && check3.isEmpty()){
                        return ResponseEntity.status(Error.OK).body(
                                new ResponseObject(true,Error.OK_MESSAGE, "Delete Receipt Success !!!")
                        );
                    }else{
                        return ResponseEntity.status(Error.FAIL).body(
                                new ResponseObject(false,Error.FAIL_MESSAGE, "Delete Receipt Fail !!!")
                        );
                    }
                }else{
                    return ResponseEntity.status(Error.FAIL).body(
                            new ResponseObject(false,Error.FAIL_MESSAGE, "This Is Not Receipt Error !!!")
                    );
                }
            }else{
                return ResponseEntity.status(Error.FAIL).body(
                        new ResponseObject(false,Error.FAIL_MESSAGE, "Receipt Not Exist !!!")
                );
            }

    }
}
