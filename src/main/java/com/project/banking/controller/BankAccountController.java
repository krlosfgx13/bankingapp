package com.project.banking.controller;

import com.project.banking.model.BankAccount;
import com.project.banking.response.ApiResponse;
import com.project.banking.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BankAccountController {

    @Autowired
    BankAccountService service;

    @PostMapping("/bankAccount")
    public ApiResponse createBankAccount(@RequestBody BankAccount bankAccount){
        try{
            return service.createBankAccount(bankAccount);
        }catch (Exception ex){
            return new ApiResponse(500, "Something went wrong", "error");
        }
    }

    @GetMapping("/bankAccount")
    public List<BankAccount> getAllBankBankAccounts(){
        try {
            return service.getAllBankAccounts();
        }catch (Exception ex){
            return  null;
        }
    }

    @GetMapping("/bankAccount/{id}")
    public BankAccount getBankAccountById(@PathVariable(value="id") int id){
        try {
            return  service.getBankAccountById(id);
        }catch (Exception ex){
            return  null;
        }
    }

    @PutMapping("/bankAccount/{id}")
    public ApiResponse updateBankAccount(@PathVariable(value="id") int id, float amount){
        try {
            return service.updateBankAccount(id, amount);
        }catch (Exception ex){
            return new ApiResponse(500, "Something went wrong", "error");
        }
    }

    @DeleteMapping("/bankAccount/{id}")
    public ApiResponse DeleteBankAccount(@PathVariable(value="id") int id){
        try {
            return service.deleteBankAccount(id);
        }catch (Exception ex){
            return new ApiResponse(500, "Something went wrong", "error");
        }
    }
}
