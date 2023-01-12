package com.project.banking.service;

import com.project.banking.model.BankAccount;
import com.project.banking.response.ApiResponse;

import java.util.List;

public interface BankAccountService {

    ApiResponse createBankAccount(BankAccount bankAccount);
    ApiResponse updateBankAccount(Integer id, float amount);
    ApiResponse deleteBankAccount(Integer id);
    BankAccount getBankAccountById(Integer id);
    BankAccount getBankAccountByDpi(Integer id);
    List<BankAccount> getAllBankAccounts();
}
