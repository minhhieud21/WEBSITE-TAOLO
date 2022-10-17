package com.example.JavaSpring.controllers;

import com.example.JavaSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/user") //localhost:8080/api/v1/account
@CrossOrigin(origins ="http://localhost:4200")
public class UserController {
    @Autowired
    UserService userService;

}
