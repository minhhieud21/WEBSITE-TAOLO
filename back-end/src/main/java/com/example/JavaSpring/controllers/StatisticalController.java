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
import java.util.*;


@RestController
@RequestMapping(path = "/api/v1/statistical") //localhost:8080/api/v1/statistical
@CrossOrigin(origins ="*")
public class  StatisticalController {
    @Autowired
    BillService billService;
    @Autowired
    BillDetailService billDetailService;

    String AfterDay(int day,int month,int year){
        Calendar cal = Calendar.getInstance();
        cal.set(year,month,day);
        cal.add(Calendar.DAY_OF_MONTH,-1);
        return cal.get(Calendar.YEAR)+"-"+(Integer.valueOf(cal.get(Calendar.MONTH)))+"-"+cal.get(Calendar.DAY_OF_MONTH);
    }

    String AfterMonth(int month,int year){
        Calendar cal = Calendar.getInstance();
        cal.set(year,month,1);
        cal.add(Calendar.DAY_OF_MONTH,-1);
        return cal.get(Calendar.YEAR)+"-"+(Integer.valueOf(cal.get(Calendar.MONTH))+1);
    }

    @GetMapping("/getStatisticalService")
    ResponseEntity<ResponseObject> getProductByCateID(@RequestParam(defaultValue = "0") int type,@RequestParam(defaultValue = "0") int day,@RequestParam(defaultValue = "0") int month,@RequestParam(defaultValue = "0") int year) {
        if(type >1 || type<0 || day < 0 || day > 31 || month < 0 || month > 12  ){
            return ResponseEntity.status(Error.DATA_REQUEST_ERROR).body(
                    new ResponseObject(false,Error.DATA_REQUEST_ERROR_MESSAGE,"")
            );
        }
        if(day == 0 && month == 0 && year == 0 || month == 0 || year == 0 || day == 0){
                LocalDate localDate = LocalDate.now();
                year = localDate.getYear();
                month = localDate.getMonthValue();
                day = localDate.getDayOfMonth();
        }
        String date = "";
        String afterdate = "";
        if(type == 0){
            date = year+"-"+month+"-"+day;
            afterdate = AfterDay(day,month,year);
        }
        else if(type == 1){
            date = year+"-"+month;
            afterdate = AfterMonth((month-1),year);
        }
        HashMap<String,Object> object = new HashMap<String,Object>();
        object.put("tongdoanhthu",Tongdoanhthu(date,afterdate));
        object.put("tongsoluong",ThongKeSoLuong(date,afterdate));
        object.put("topsanpham",thongkesanpham(date));
        return ResponseEntity.status(Error.OK).body(
                new ResponseObject(true,Error.OK_MESSAGE,object)
        );
    }
    public long[] Tongdoanhthu(String Date,String afterDate){
        List<BillDetailModel> a = billDetailService.getBillDetailByDay(Date);
        long ketqua[] = new long[2] ;
        if(a.isEmpty() == false){
            ketqua[0]=0;
            ketqua[1]=0;
            List<String> dsBill = new ArrayList<>();
            for (int i = 0 ; i < a.size();i++){
                dsBill.add(a.get(i).getBillID());
            }
            if(dsBill.isEmpty() == false){
                for (int i =0 ; i < dsBill.size();i++){
                    ketqua[0]=ketqua[0]+billService.getBillByDayDone(dsBill.get(i)).getTotalCost();
                }}
        }
        List<BillDetailModel> aa = billDetailService.getBillDetailByDay(afterDate);
        if(aa.isEmpty()==false){
            List<String> dsBilla = new ArrayList<>();
            for (int i = 0 ; i < aa.size();i++){
                dsBilla.add(aa.get(i).getBillID());
            }
            if (dsBilla.isEmpty() == false ){
                for (int i =0 ; i < dsBilla.size();i++){
                    ketqua[1]=ketqua[1]+billService.getBillByDayDone(dsBilla.get(i)).getTotalCost();
                }}}
        ketqua[1] = ketqua[0] - ketqua[1];
        return ketqua;
    }
    public long[] ThongKeSoLuong(String Date,String afterDate){
        List<BillDetailModel> a = billDetailService.getBillDetailByDay(Date);
        long ketqua[] = new long[2] ;
        if(a.isEmpty() == false){
            ketqua[0]=0;
            ketqua[1]=0;
            List<String> dsBill = new ArrayList<>();
            for (int i = 0 ; i < a.size();i++){
                dsBill.add(a.get(i).getBillID());
            }
            if(dsBill.isEmpty() == false){
                for (int i =0 ; i < dsBill.size();i++){
                    ketqua[0]=ketqua[0]+billService.getBillByDayDone(dsBill.get(i)).getToTalQuantity();
                }}
        }
        List<BillDetailModel> aa = billDetailService.getBillDetailByDay(afterDate);
        if(aa.isEmpty()==false){
            List<String> dsBilla = new ArrayList<>();
            for (int i = 0 ; i < aa.size();i++){
                dsBilla.add(aa.get(i).getBillID());
            }
            if (dsBilla.isEmpty() == false ){
                for (int i =0 ; i < dsBilla.size();i++){
                    ketqua[1]=ketqua[1]+billService.getBillByDayDone(dsBilla.get(i)).getToTalQuantity();
                }}}
        ketqua[1] = ketqua[0] - ketqua[1];
        return ketqua;
    }

    public AbstractMap<String,Integer> thongkesanpham(String Date){
        List<BillDetailModel> a = billDetailService.getBillDetailByDay(Date);
        HashMap<String,Integer> object = new HashMap<String,Integer>();
        List<String> check = new ArrayList<>();
        if(a.isEmpty() == false) {
            for (int i = 0; i < a.size(); i++) {
                if(check.isEmpty() == true){
                    check.add(a.get(i).getProID());
                    object.put(a.get(i).getProID(),a.get(i).getQuantity());
                }else {
                    int dem = 0;
                    int size = check.size();
                    for (int j = 0 ; j < size ;j++){
                        if(a.get(i).getProID().equals(check.get(j)) == true){
                            int tem=a.get(i).getQuantity()+object.get(a.get(i).getProID());
                            object.remove(a.get(i).getProID());
                            object.put(a.get(i).getProID(),tem);
                            break;
                        }
                        dem++;
                        if(dem == check.size()){
                            check.add(a.get(i).getProID());
                            object.put(a.get(i).getProID(),a.get(i).getQuantity());
                        }
                    }
                }
        }}
        return object;
    }
}
