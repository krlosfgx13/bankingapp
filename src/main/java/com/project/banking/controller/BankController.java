package com.project.banking.controller;

import com.project.banking.model.Bank;
import com.project.banking.request.BankRequest;
import com.project.banking.response.ApiResponse;
import com.project.banking.response.BankCurrencyResponse;
import com.project.banking.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BankController {

    @Autowired
    BankService service;

    @PostMapping("/bank")
    public ApiResponse createBank(@RequestBody BankRequest bankRequest){
        try{
            Bank bank = new Bank();
            bank.setName(bankRequest.getName());
            bank.setCashAvailable(bankRequest.getCashAvailable());
            bank.setAddress(bankRequest.getAddress());
            List<Integer> currencies = bankRequest.getListOfCurrencies();
            return service.createBank(bank, currencies);
        }catch (Exception ex){
            return new ApiResponse(500, "Something went wrong", "error");
        }
    }

    @GetMapping("/bank")
    public List<Bank> getAllBanks(){
        try{
            return service.getAllBanks();
        }catch (Exception ex){
            return null;
        }
    }

    @GetMapping("/bank/{id}")
    public Bank getBankById(@PathVariable(value="id") int id){
        return null;
    }

    @PutMapping("/bank/{id}")
    public ApiResponse updateBank(@PathVariable(value="id") int id, @RequestBody Bank request){
        return null;
    }

    @DeleteMapping("/bank/{id}")
    public ApiResponse DeleteBank(@PathVariable(value="id") int id){
        return null;
    }

    @GetMapping("/bank/currencies")
    public BankCurrencyResponse getAllBankCurrencies(){
        try{
            return service.getAllBankCurrencies();
        }catch (Exception ex){
            return null;
        }
    }
}
