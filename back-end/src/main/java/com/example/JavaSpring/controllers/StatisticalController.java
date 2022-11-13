package com.example.JavaSpring.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/statistical") //localhost:8080/api/v1/statistical
@CrossOrigin(origins ="*")
public class  StatisticalController {
}
