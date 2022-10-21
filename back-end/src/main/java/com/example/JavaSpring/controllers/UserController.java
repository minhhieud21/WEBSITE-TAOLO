package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.UserModel;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.UserService;
import com.example.JavaSpring.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping(path = "/api/v1/user") //localhost:8080/api/v1/account
@CrossOrigin(origins ="http://localhost:4200")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("")
    ResponseEntity<ResponseObject> getallUser() {
        List<UserModel> check = userService.getAllUser();
        if (check != null) {
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE,check)
            );
        } else {
            return ResponseEntity.status(Error.LIST_EMPTY).body(
                    new ResponseObject(false,Error.LIST_EMPTY_MESSAGE, "")
            );
        }
    }
    @GetMapping("/{userID}")
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
}
