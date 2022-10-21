package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.AccountModel;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.models.UserModel;
import com.example.JavaSpring.service.AccountService;
import com.example.JavaSpring.service.UserService;
import com.example.JavaSpring.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "/api/v1/account") //localhost:8080/api/v1/account
@CrossOrigin(origins ="*")
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    UserService userService;

    public String convertHashToString(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(text.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public String createID(String text) {
       char kt1 = text.charAt(2);
       char kt2 = text.charAt(3);
       char kt3 = text.charAt(4);
       int kt = Integer.parseInt(String.valueOf(kt1)+String.valueOf(kt2)+String.valueOf(kt3));
       kt = kt + 1;
        String ketqua;
       if(kt < 10){
            ketqua = "US00" + String.valueOf(kt);
       }
       else if (kt < 100){
            ketqua = "US0" + String.valueOf(kt);
       }
       else {
            ketqua = "US" + String.valueOf(kt);
       }
        return ketqua;
    }
    public String createAdminID(String text) {
        char kt1 = text.charAt(2);
        char kt2 = text.charAt(3);
        char kt3 = text.charAt(4);
        int kt = Integer.parseInt(String.valueOf(kt1)+String.valueOf(kt2)+String.valueOf(kt3));
        kt = kt + 1;
        String ketqua;
        if(kt < 10){
            ketqua = "AD00" + String.valueOf(kt);
        }
        else if (kt < 100){
            ketqua = "AD0" + String.valueOf(kt);
        }
        else {
            ketqua = "AD" + String.valueOf(kt);
        }
        return ketqua;
    }
    public int Number(String text) {
        char kt1 = text.charAt(2);
        char kt2 = text.charAt(3);
        char kt3 = text.charAt(4);
        int kt = Integer.parseInt(String.valueOf(kt1)+String.valueOf(kt2)+String.valueOf(kt3));
        return kt;
    }

    @GetMapping("/login")
        ResponseEntity<ResponseObject> getUserByUsername(@RequestBody(required = false) Map<String,Object> object,@RequestParam(required = false) boolean google_login, @RequestParam(defaultValue = "notthing") String urlID) throws NoSuchAlgorithmException {
        AccountModel check = new AccountModel();
        AccountModel accountModel = new AccountModel();
        if(google_login == true && urlID.equals("notthing")==true ) {
            return ResponseEntity.status(Error.WRONG_ACCESS_RIGHTS).body(
                    new ResponseObject(false,Error.WRONG_ACCESS_RIGHTS_MESSAGE,"" )
            );
        }
        if(google_login == true){
            check = accountService.getUserByUrlID(urlID);
            if(check == null){
                int max = 0,vt=0,i;
                List<AccountModel> dsAccount = accountService.getAllAccount();
                for(i = 0 ; i<dsAccount.size(); i++){
                    if (Number(dsAccount.get(i).getAccID())>max && "U".equals(String.valueOf(dsAccount.get(i).getAccID().charAt(0))) ){
                        vt = i;
                    }
                }
                accountModel.setAccID(createID(dsAccount.get(vt).getAccID()));
                accountModel.setUsername("nothave");
                accountModel.setPassword("nothave");
                accountModel.setStatus(1);
                accountModel.setGoogle_login(true);
                accountModel.setUrlID(urlID);
                accountService.saveAccount(accountModel);
                return ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, accountModel.getAccID())
                );
                }
            else {
                if(check.getStatus() == 1){
                   return ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, check.getAccID())
                   );}
                else {
                   return ResponseEntity.status(Error.WRONG_STATUS).body(
                        new ResponseObject(false,Error.WRONG_STATUS_MESSAGE, ""));
                }
            }
        }
        else {
            if(String.valueOf(object.get("username")).equals("nothave") == true){
                return ResponseEntity.status(Error.WRONG_ACCESS_RIGHTS).body(
                        new ResponseObject(false,Error.WRONG_ACCESS_RIGHTS_MESSAGE,"" )
                );
            }
                check = accountService.getUserByUsername(String.valueOf(object.get("username")).toLowerCase());
            if(check == null){
                return ResponseEntity.status(Error.WRONG_USERNAME).body(
                        new ResponseObject(false, Error.WRONG_USERNAME_MESSAGE,"")
                );}
            else {
                String checkPassword = convertHashToString(String.valueOf(object.get("password")));
                if(check.getPassword().equals(checkPassword)){
                    if(check.getStatus() == 1){
                        return ResponseEntity.status(Error.OK).body(
                                new ResponseObject(true,Error.OK_MESSAGE, check.getAccID())
                        );}
                    else {
                        return ResponseEntity.status(Error.WRONG_STATUS).body(
                                new ResponseObject(false,Error.WRONG_STATUS_MESSAGE, ""));
                    }
                }
                else {
                    return ResponseEntity.status(Error.WRONG_PASSWORD).body(
                            new ResponseObject(false,Error.WRONG_PASSWORD_MESSAGE, ""));
                }
            }
        }
    }

    @PostMapping("/addUser")
    ResponseEntity<ResponseObject> addnewUser(@RequestBody Map<String,Object> object,@RequestParam(defaultValue = "1") int type) throws NoSuchAlgorithmException {
        AccountModel check = accountService.getUserByUsername(String.valueOf(object.get("username")));
        if (check != null) {
            return ResponseEntity.status(Error.DUPLICATE_ID).body(
                    new ResponseObject(false, Error.DUPLICATE_ID_MESSAGE, "")
            );
        } else {
            int max = 0, vt = 0, i;
            List<AccountModel> dsAccount = accountService.getAllAccount();
            if(type==1){
                for (i = 0; i < dsAccount.size(); i++) {
                    if (Number(dsAccount.get(i).getAccID()) > max && "U".equals(String.valueOf(dsAccount.get(i).getAccID().charAt(0)))) {
                        vt = i;
                    }
                }}
            else if(type == 0){
                for(i = 0 ; i<dsAccount.size(); i++){
                    if (Number(dsAccount.get(i).getAccID())>max && "A".equals(String.valueOf(dsAccount.get(i).getAccID().charAt(0))) ){
                        vt = i;
                    }
                }
            }
            AccountModel accountModel = new AccountModel();
            UserModel userModel = new UserModel();
            if(type==1){
                accountModel.setAccID(createID(dsAccount.get(vt).getAccID()));
            }
            else if(type == 0){
                accountModel.setAccID(createAdminID(dsAccount.get(vt).getAccID()));
            }
            accountModel.setUsername(String.valueOf(object.get("username")));
            String password = convertHashToString(String.valueOf(object.get("password")));
            accountModel.setPassword(password);
            accountModel.setStatus(1);
            accountModel.setGoogle_login(false);
            accountModel.setUrlID("nothave");
            accountService.saveAccount(accountModel);
            userModel.setName(String.valueOf(object.get("name")));
            userModel.setPhone(String.valueOf(object.get("phone")));
            userModel.setAddress(String.valueOf(object.get("address")));
            userModel.setGmail(String.valueOf(object.get("gmail")));
            userModel.setSex(Integer.parseInt(String.valueOf(object.get("sex"))));
            userModel.setAge(Integer.parseInt(String.valueOf(object.get("age"))));
            userService.saveUser(userModel);
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true, Error.OK_MESSAGE, "")
            );
        }
    }

}
