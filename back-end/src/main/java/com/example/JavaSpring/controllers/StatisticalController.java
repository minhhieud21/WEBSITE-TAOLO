package com.example.JavaSpring.controllers;


import com.example.JavaSpring.models.BillDetailModel;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.BillDetailService;
import com.example.JavaSpring.service.BillService;
import com.example.JavaSpring.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "/api/v1/statistical") //localhost:8080/api/v1/statistical
@CrossOrigin(origins ="*")
public class  StatisticalController {
    @Autowired
    BillService billService;
    @Autowired
    BillDetailService billDetailService;
    @GetMapping("/getStatisticalService")
    ResponseEntity<ResponseObject> getProductByCateID(@RequestParam(defaultValue = "0") int type,@RequestParam(defaultValue = "0") int day,@RequestParam(defaultValue = "0") int month,@RequestParam(defaultValue = "0") int year) {
        if(day == 0 && month == 0 && year == 0){
                LocalDate localDate = LocalDate.now();
                year = localDate.getYear();
                month = localDate.getMonthValue();
                day = localDate.getDayOfMonth();
        }
        System.out.println(day);
        System.out.println(month);
        System.out.println(year);
        return null;
    }
    @GetMapping("")
    public long Tondoanhthu(){
        String temp = "2022-11-12";
        List<BillDetailModel> a = billDetailService.getBillDetailByDay(temp);
        List<String> dsBill = new ArrayList<>();
        for (int i = 0 ; i < a.size();i++){
            dsBill.add(a.get(i).getBillID());
        }
        return 0;
    }
}
