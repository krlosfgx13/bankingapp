package com.project.banking.service;

import com.project.banking.model.BankAccount;
import com.project.banking.response.ApiResponse;

import java.util.List;

public interface BankAccountService {

    ApiResponse createBankAccount(BankAccount bankAccount);
    ApiResponse updateBankAccount(Integer id, double amount);
    ApiResponse deleteBankAccount(Integer id);
    BankAccount getBankAccountById(Integer id);
    BankAccount getBankAccountByPersonId(Integer id);
    List<BankAccount> getAllBankAccounts();
}
