package com.project.banking.controller;

import com.project.banking.model.Currency;
import com.project.banking.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    CurrencyService currencyService;

    @PostMapping("/config")
    public ResponseEntity<Object> createCurrency(@RequestBody Currency currency){
        currencyService.createCurrency(currency);
        return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/hello")
    public String HelloWorld(){
        return "Hello World!";
    }


}
