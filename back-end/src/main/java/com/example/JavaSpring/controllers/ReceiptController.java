package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.ProductModel;
import com.example.JavaSpring.models.ReceiptDetailModel;
import com.example.JavaSpring.models.ReceiptModel;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.ReceiptDetailService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/receipt") //localhost:8080/api/v1/receipt
@CrossOrigin(origins = "*")
public class ReceiptController {

    @Autowired
    ReceiptService receiptService;

    @Autowired
    ReceiptDetailServicelmpl receiptDetailService;

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

//    @PostMapping("/showReceiptExcelFile")
//    ResponseEntity<ResponseObject> addReceipt(@RequestParam("list") MultipartFile list) throws IOException{
//        XSSFWorkbook workbook = new XSSFWorkbook(list.getInputStream());
//        XSSFSheet worksheet = workbook.getSheetAt(0);
//        List<ProductModel> NewProList = new ArrayList<ProductModel>();
//        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
//            XSSFRow row = worksheet.getRow(i);
//            ProductModel NewPro = new ProductModel();
//            NewPro.setproId(row.getCell(1).getStringCellValue());
//            NewPro.setProName(row.getCell(2).getStringCellValue());
//            NewPro.setDescription(row.getCell(3).getStringCellValue());
//            NewPro.setPrice(row.getCell(4).getCTCell().getS());
//            NewPro.setCateId(row.getCell(5).getStringCellValue());
//            NewPro.setColor(row.getCell(6).getStringCellValue());
//            NewPro.setQuantity(row.getCell(7).getColumnIndex());
//            NewPro.setWarrantyMonth(row.getCell(8).getColumnIndex());
//            NewProList.add(NewPro);
//        }
//        if(NewProList.size() > 0){
//            return ResponseEntity.status(Error.OK).body(
//                    new ResponseObject(true,Error.OK_MESSAGE, NewProList)
//            );
//        }else{
//            return ResponseEntity.status(Error.FAIL).body(
//                    new ResponseObject(false,Error.FAIL_MESSAGE, "Can Not read Data In File !!!")
//            );
//        }
//    }
}
