package com.project.banking.controller;

import com.project.banking.model.BankAccount;
import com.project.banking.model.Transaction;
import com.project.banking.request.TransactionRequest;
import com.project.banking.response.ApiResponse;
import com.project.banking.response.BalanceInquiryResponse;
import com.project.banking.response.TransactionLogResponse;
import com.project.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    TransactionService service;

    @PostMapping("/transaction/depositMoney")
    public ApiResponse depositMoney(@RequestBody TransactionRequest transaction){
        try {
            return service.depositMoney(transaction.getBankAccountId(), transaction.getCurrencyId(),
                    transaction.getAmount());
        }catch (Exception ex){
            return new ApiResponse(500, "Something went wrong", "error");
        }
    }

    @PostMapping("/transaction/balanceInquiry")//post?
    public BalanceInquiryResponse balanceInquiry(@RequestBody TransactionRequest transaction){
        try {
            return service.balanceInquiry(transaction.getAtmId(), transaction.getUserName(), transaction.getPassword());
        }catch (Exception ex){
            return null;
        }
    }

    @PostMapping("/transaction/withdrawMoney")
    public ApiResponse withdrawMoney(@RequestBody TransactionRequest transaction){
        try {
            return service.withdrawMoney(transaction.getAtmId(), transaction.getUserName(), transaction.getPassword(),
                    transaction.getAmount());
        }catch (Exception ex){
            return new ApiResponse(500, "Something went wrong", "error");
        }
    }

    @PostMapping("/transaction/depositMoneyWithPaycheck")
    public ApiResponse depositMoneyWithPayCheck(@RequestBody TransactionRequest transaction){
        try {
            return service.depositMoneyWithPayCheck(transaction.getAtmId(), transaction.getUserName(),
                    transaction.getPassword(), transaction.getBankName(), transaction.getCurrencyId(),
                    transaction.getAmount(), transaction.getBankAccountId());
        }catch (Exception ex){
            return new ApiResponse(500, "Something went wrong", "error");
        }
    }

    @PostMapping("/transaction/monetaryInvestment")
    public ApiResponse monetaryInvestment(@RequestBody TransactionRequest transaction){
        try {
            return service.monetaryInvestment(transaction.getBankId(), transaction.getCurrencyId(),
                    transaction.getAmount());
        }catch (Exception ex){
            return new ApiResponse(500, "Something went wrong", "error");
        }
    }

    @PostMapping("/transaction/rechargeAtm")
    public ApiResponse rechargeAtm(@RequestBody TransactionRequest transaction){
        try {
            return service.rechargeAtm(transaction.getAtmId(), transaction.getCurrencyId(), transaction.getAmount());
        }catch (Exception ex){
            return new ApiResponse(500, "Something went wrong", "error");
        }
    }

    @GetMapping("/transaction/log")
    public TransactionLogResponse getTransactionLog(){
        try{
            return service.getTransactionLog();
        }catch (Exception ex){
            return null;
        }
    }
}
