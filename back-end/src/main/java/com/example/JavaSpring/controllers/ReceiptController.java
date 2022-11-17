package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.*;
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

    Map<String,String> imgA = null;

    MultipartFile[] imgV = null;

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

    @PostMapping("/showReceiptExcelFile")
    ResponseEntity<ResponseObject> addReceipt(@RequestParam("list") MultipartFile[] list) throws IOException{
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
