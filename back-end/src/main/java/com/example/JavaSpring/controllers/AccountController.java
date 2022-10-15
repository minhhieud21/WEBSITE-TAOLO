package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.AccountModel;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.AccountService;
import com.example.JavaSpring.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/account") //localhost:8080/api/v1/account
@CrossOrigin(origins ="http://localhost:4200")
public class AccountController {
    @Autowired
    AccountService accountService;

    public String convertHashToString(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(text.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @GetMapping("/checklogin")
    ResponseEntity<ResponseObject> getUserByUsername(@RequestBody AccountModel accountModel) throws NoSuchAlgorithmException {
        AccountModel check = accountService.getUserByUsername(accountModel.getUsername().toLowerCase());
        if(check == null){
                return ResponseEntity.status(Error.WRONG_USERNAME).body(
                        new ResponseObject(false, Error.WRONG_USERNAME_MESSAGE,"")
                );}
        else {
            String checkPassword = convertHashToString(accountModel.getPassword());
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
    @GetMapping("/loginadmin")
    ResponseEntity<ResponseObject> checkadmin(@RequestBody AccountModel accountModel) throws NoSuchAlgorithmException {
        AccountModel check = accountService.getUserByUsername(accountModel.getUsername().toLowerCase());
        if(check == null){
            return ResponseEntity.status(Error.WRONG_USERNAME).body(
                    new ResponseObject(false, Error.WRONG_USERNAME_MESSAGE,"")
            );}
        else {
            String checkPassword = convertHashToString(accountModel.getPassword());
            if(check.getPassword().equals(checkPassword)){
                if(check.getAccID().contains("AM") == true){
                    return ResponseEntity.status(Error.OK).body(
                            new ResponseObject(true,Error.OK_MESSAGE,check.getAccID())
                    );}
                else {
                    return ResponseEntity.status(Error.WRONG_ACCESS_RIGHTS ).body(
                            new ResponseObject(false,Error.WRONG_ACCESS_RIGHTS_MESSAGE, ""));
                }
            }
            else {
                return ResponseEntity.status(Error.WRONG_PASSWORD).body(
                        new ResponseObject(false,Error.WRONG_PASSWORD_MESSAGE, ""));
            }
        }
    }
}
