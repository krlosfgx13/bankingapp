package com.project.banking.service.impl;

import com.project.banking.model.Atm;
import com.project.banking.model.Bank;
import com.project.banking.model.BankAccount;
import com.project.banking.model.User;
import com.project.banking.repository.BankAccountRepository;
import com.project.banking.response.ApiResponse;
import com.project.banking.service.BankAccountService;
import com.project.banking.service.BankService;
import com.project.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository repository;
    private final BankService bankService;
    private final UserService userService;

    public BankAccountServiceImpl(BankAccountRepository repository, BankService bankService, UserService userService){
        this.repository = repository;
        this.bankService = bankService;
        this.userService = userService;
    }
    

    ApiResponse response = new ApiResponse();

    @Override
    public ApiResponse createBankAccount(BankAccount bankAccount) {
        try{
            Bank bank = bankService.getBankById(bankAccount.getBankId());
            User user = userService.getUserById(bankAccount.getDpi());

            if(user != null){
                if(bank != null){
                    if(bankAccount.getBalance() >= 200.00f){
                        repository.save(bankAccount);
                        response.setMessage("Operacion realizada con exito.");
                        response.setCode(200);
                        response.setStatus("success");
                    }else{
                        response.setMessage("Cantidad no puede ser menor a Q200.00");
                        response.setCode(500);
                        response.setStatus("error");
                    }
                    return response;
                }else{
                    return new ApiResponse(500, "Banco no valido.", "error");
                }
            }else{
                return new ApiResponse(500, "DPI no valido.", "error");
            }

        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public ApiResponse updateBankAccount(Integer id, float amount) {
        try {
            BankAccount bankAccountObj = repository.findByAccountId(id);
            if(bankAccountObj != null){
                bankAccountObj.setBalance(amount);
                repository.save(bankAccountObj);
                return new ApiResponse(200, "Operacion realizada con exito.", "success");
            }else {
                return new ApiResponse(500, "Ha ocurrido un error.", "error");
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
                return new ApiResponse(200, "Operacion realizada con exito.", "success");
            }else{
                return new ApiResponse(500, "Ha ocurrido un error.", "error");
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

    public BankAccount getBankAccountByDpi(Long id){
        try{
            return repository.findByDpi(id);
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
