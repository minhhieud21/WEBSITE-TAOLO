package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.models.UserModel;
import com.example.JavaSpring.service.JwtService;
import com.example.JavaSpring.service.UserService;
import com.example.JavaSpring.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/user") //localhost:8080/api/v1/account
@CrossOrigin(origins ="*")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @GetMapping("/getUserByID/{userID}")
    ResponseEntity<ResponseObject> addUser(@PathVariable String userID) {
        UserModel check = userService.getUserByUserID(userID);
        if (check != null) {
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE, check)
            );
        } else {
            return ResponseEntity.status(Error.NO_VALUE_BY_ID).body(
                    new ResponseObject(false,Error.NO_VALUE_BY_ID_MESSAGE,"")
            );
        }
    }


    @PostMapping("updateUser")
    ResponseEntity<ResponseObject> updateUser(ServletRequest request, @RequestParam("name")String name, @RequestParam("phone") String phone, @RequestParam("address") String address, @RequestParam("sex") int sex, @RequestParam("age")int age){
        if( name.length() == 0 || phone.length() == 0 || address.length() == 0 ||  phone.length()<= 9  ){
            return ResponseEntity.status(Error.DATA_REQUEST_ERROR).body(
                    new ResponseObject(false,Error.DATA_REQUEST_ERROR_MESSAGE,"")
            );
        }
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authToken = httpRequest.getHeader("authorization");
        String accID = null;
        if (jwtService.validateTokenLogin(authToken)) {
            accID = jwtService.getAccIDFromToken(authToken);}
        UserModel newUser = new UserModel();
        newUser.setUserID(accID);
        newUser.setName(name);
        newUser.setPhone(phone);
        newUser.setAddress(address);
        newUser.setAge(age);
        newUser.setSex(sex);
        UserModel userModel1 = userService.getUserByUserID(newUser.getUserID());
        if(userModel1 != null ){
            userService.updateUser(newUser,newUser.getUserID());
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE,"")
            );}
        else {
            return ResponseEntity.status(Error.NO_VALUE_BY_ID).body(
                    new ResponseObject(false, Error.NO_VALUE_BY_ID_MESSAGE,"")
            );}
    }
}
