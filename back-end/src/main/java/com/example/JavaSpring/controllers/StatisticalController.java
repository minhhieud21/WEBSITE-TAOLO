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
    ResponseEntity<ResponseObject> getProductByCateID(@RequestParam(defaultValue = "0") int year) {
        if(year > LocalDate.now().getYear()){
            return ResponseEntity.status(Error.FAIL).body(
                    new ResponseObject(false,"Nam khong the lon hon nam hien tai","")
            );
        }
        if(year == 0){
                LocalDate localDate = LocalDate.now();
                year = localDate.getYear();
        }
        String date = "";
        long temp[] = new long[12];
        for(int i = 1; i < 13; i++ ) {
            date = year + "-" + i+"-";
            temp[i-1]=Tongdoanhthu(date);
        }
        long ketqua[] = new long[4];
        ketqua[0]=temp[0]+temp[1]+temp[2];
        ketqua[1]=temp[3]+temp[4]+temp[5];
        ketqua[2]=temp[6]+temp[7]+temp[8];
        ketqua[3]=temp[9]+temp[10]+temp[11];
        HashMap<String,Object> object = new HashMap<String,Object>();
        object.put("tongdoanhthutheoquy",ketqua);
        object.put("tongsoluongdaban",ThongKeSoLuong(String.valueOf(year+"-")));
        object.put("topsanphambanchay",thongkesanpham(String.valueOf(year+"-")));
        return ResponseEntity.status(Error.OK).body(
                new ResponseObject(true,Error.OK_MESSAGE,object)
        );
    }
    public long Tongdoanhthu(String Date){
        List<BillDetailModel> a = billDetailService.getBillDetailByMonth(Date);
        long ketqua = 0 ;
        List<String> dsBill = new ArrayList<>();
        if(a.isEmpty() == false){
            dsBill.add(0,a.get(0).getBillID());
            for (int i = 1 ; i < a.size();i++){
                int dem = 0;
                for (int j = 0 ; j < dsBill.size();j++){
                    if(a.get(i).getBillID().equals(dsBill.get(j)) == true){
                        break;
                    }
                    dem++;
                    if(dem==dsBill.size()){
                        dsBill.add(a.get(i).getBillID());
                    }
                }
            }
        }
        if(dsBill.isEmpty() == false){
            for (int i =0 ; i < dsBill.size();i++){
                ketqua=ketqua+billService.getBillByDayDone(dsBill.get(i)).getTotalCost();
            }}
        return ketqua;
    }
    public long ThongKeSoLuong(String Date){
        List<BillDetailModel> a = null;
        a = billDetailService.getBillDetailByMonth(Date);
        long ketqua = 0;
        List<String> dsBill = new ArrayList<>();
        if(a.isEmpty() == false){
            dsBill.add(0,a.get(0).getBillID());
            for (int i = 1 ; i < a.size();i++){
                int dem = 0;
                for (int j = 0 ; j < dsBill.size();j++){
                    if(a.get(i).getBillID().equals(dsBill.get(j)) == true){
                        break;
                    }
                    dem++;
                    if(dem==dsBill.size()){
                        dsBill.add(a.get(i).getBillID());
                    }
                }
            }
        }
        if(dsBill.isEmpty() == false){
            for (int i =0 ; i < dsBill.size();i++){
                ketqua=ketqua+billService.getBillByDayDone(dsBill.get(i)).getToTalQuantity();
            }}
        return ketqua;
    }


    public AbstractMap<String,Integer> thongkesanpham(String Date){
        List<BillDetailModel> a = null;
        a = billDetailService.getBillDetailByMonth(Date);
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
        return sortByValue(object);
    }
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
