package com.project.banking.service.impl;

import com.project.banking.model.Bank;
import com.project.banking.model.BankAccount;
import com.project.banking.model.User;
import com.project.banking.repository.BankAccountRepository;
import com.project.banking.response.ApiResponse;
import com.project.banking.service.BankAccountService;
import com.project.banking.service.BankService;
import com.project.banking.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository repository;
    private final BankService bankService;
    private final UserService userService;

    public BankAccountServiceImpl(BankAccountRepository repository, BankService bankService, UserService userService){
        this.repository = repository; //comment1.
        this.bankService = bankService; //comment2.
        this.userService = userService; //comment3.
    }

    ApiResponse response = new ApiResponse();

    @Override
    public ApiResponse createBankAccount(BankAccount bankAccount) {
        try{
            Bank bank = bankService.getBankById(bankAccount.getBankId());
            User user = userService.getUserById(bankAccount.getPersonId());

            if(user != null){
                if(bank != null){
                    if(bankAccount.getBalance() >= 200.00f){
                        repository.save(bankAccount);
                        response.setMessage("Operation performed successfully.");
                        response.setCode(200);
                        response.setStatus("success");
                    }else{
                        response.setMessage("Amount cannot be more than Q200.00");
                        response.setCode(500);
                        response.setStatus("error");
                    }
                    return response;
                }else{
                    return new ApiResponse(500, "Bank is not valid.", "error");
                }
            }else{
                return new ApiResponse(500, "DPI is not valid.", "error");
            }

        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public ApiResponse updateBankAccount(Integer id, double amount) {
        try {
            BankAccount bankAccountObj = repository.findByAccountId(id);
            if(bankAccountObj != null){
                bankAccountObj.setBalance(amount);
                repository.save(bankAccountObj);
                return new ApiResponse(200, "Operation performed successfully.", "success");
            }else {
                return new ApiResponse(500, "Something went wrong.", "error");
            }

        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public ApiResponse deleteBankAccount(Integer id) {
        try{
            BankAccount bankAccount = repository.findByAccountId(id);
            if(bankAccount != null){
                repository.delete(bankAccount);
                return new ApiResponse(200, "Operation performed successfully.", "success");
            }else{
                return new ApiResponse(500, "Something went wrong.", "error");
            }
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public BankAccount getBankAccountById(Integer id) {
        try{
            return repository.findByAccountId(id);
        }catch (Exception ex){
            throw ex;
        }
    }

    public BankAccount getBankAccountByPersonId(Integer id){
        try{
            return repository.findByPersonId(id);
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public List<BankAccount> getAllBankAccounts() {
        List<BankAccount> list = new ArrayList<>();
        try{
            list = repository.findAll();
            if(!list.isEmpty()){
                return list;
            }
        }catch(Exception ex){
            throw ex;
        }
        return list;
    }
}
