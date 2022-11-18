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

//    @PostMapping("/changeReceiptStatus")
//    ResponseEntity<ResponseObject> changeReceiptStatus(String recID, int status){
//        Optional<ReceiptModel> check = Optional.ofNullable(receiptService.getReceiptByID(recID));
//        if(check.isPresent()){
//            receiptService.changeStatusReceipt(recID,status);
//            ReceiptModel receiptModel = receiptService.getReceiptByID(recID);
//            if(receiptModel.getStatus() == status){
//                return ResponseEntity.status(Error.OK).body(
//                        new ResponseObject(true, Error.OK_MESSAGE,"Change Status Receipt Success !!!")
//                );
//            }else{
//                return ResponseEntity.status(Error.FAIL).body(
//                        new ResponseObject(false, Error.FAIL_MESSAGE,"Change Status Receipt Fail !!!")
//                );
//            }
//        }else{
//            return ResponseEntity.status(Error.FAIL).body(
//                    new ResponseObject(false, Error.FAIL_MESSAGE,"Receipt Not Exist !!!")
//            );
//        }
//
//    }
//
//    String date(){
//        long millis=System.currentTimeMillis();
//        java.sql.Date date = new java.sql.Date(millis);
//        return date.toString();
//    }
//
//    @PostMapping("/addReceipt")
//    ResponseEntity<ResponseObject> addReceipt(@RequestBody List<Map<String,String>> list, @RequestParam("file") MultipartFile[] listFile) throws IOException {
//        int ck = 0;
//        for(int i = 0; i < list.size(); i++){
//            if(list.get(i).get("proID") != null && list.get(i).get("proName") != null && list.get(i).get("description") != null && list.get(i).get("price") != null && list.get(i).get("cost") != null && list.get(i).get("totalCost") != null && list.get(i).get("cateID") != null && list.get(i).get("color") != null && list.get(i).get("quantity") != null && list.get(i).get("warrantyMonth") != null){
//                if(list.get(i).get("proID") != "" && list.get(i).get("proName") != "" && list.get(i).get("description") != "" && list.get(i).get("price") != "" && list.get(i).get("cost") != "" && list.get(i).get("totalCost") != "" && list.get(i).get("cateID") != "" && list.get(i).get("color") != "" && list.get(i).get("quantity") != "" && list.get(i).get("warrantyMonth") != ""){
//                    CategoryModel categoryModel = categoryService.getCateByID(list.get(i).get("cateID"));
//                    Optional<CategoryModel> check = Optional.ofNullable(categoryModel);
//                    if(check.isPresent()){
//                        ck = 1;
//                    }else{
//                        return ResponseEntity.status(Error.FAIL).body(
//                                new ResponseObject(false, Error.FAIL_MESSAGE,"Category Is Not Exist !!!")
//                        );
//                    }
//                }else{
//                    return ResponseEntity.status(Error.FAIL).body(
//                            new ResponseObject(false, Error.FAIL_MESSAGE,"Missing Data !!!")
//                    );
//                }
//            }else{
//                return ResponseEntity.status(Error.FAIL).body(
//                        new ResponseObject(false, Error.FAIL_MESSAGE,"Missing Data !!!")
//                );
//            }
//        }
//
//        if(ck == 1){
//            String proID[] = new String[list.size()];
//            String proName[] = new String[list.size()];
//            String description[] = new String[list.size()];
//            long price[] = new long[list.size()];
//            String cateID[] = new String[list.size()];
//            String color[] = new String[list.size()];
//            int quantity[] = new int[list.size()];
//            int warrantyMonth[] = new int[list.size()];
//            for(int i = 0; i < list.size(); i++){
//                proID[i] = list.get(i).get("proID");
//                proName[i] = list.get(i).get("proName");
//                description[i] = list.get(i).get("description");
//                price[i] =  Long.parseLong(list.get(i).get("price"));
//                cateID[i] = list.get(i).get("cateID");
//                color[i] = list.get(i).get("color");
//                quantity[i] = Integer.parseInt(list.get(i).get("quantity"));
//                warrantyMonth[i] = Integer.parseInt(list.get(i).get("warrantyMonth"));
//            }
//            productController.addListProduct(proID,proName,description,price,cateID,color,quantity,warrantyMonth,listFile);
//            String recID = receiptService.autoID();
//            for(int i = 0; i < list.size() ; i++){
//                ProductModel productModel = productService.getProductById(list.get(i).get("proID"));
//                Optional<ProductModel> check = Optional.of()
//                if()
//            }
//
//        }else{
//            return ResponseEntity.status(Error.FAIL).body(
//                    new ResponseObject(false, Error.FAIL_MESSAGE,"Category Is Not Exist !!!")
//            );
//        }
//    }

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
