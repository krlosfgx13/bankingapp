package com.project.banking.service;

import com.project.banking.model.Bank;
import com.project.banking.model.BankCurrency;
import com.project.banking.response.ApiResponse;
import com.project.banking.response.BankCurrencyResponse;

import java.sql.SQLException;
import java.util.List;

public interface BankService {

    ApiResponse createBank(Bank bank, List<Integer> bankCurrencies);
    ApiResponse updateBank(Integer id, float amount);
    ApiResponse deleteBank(Integer id);
    Bank getBankById(Integer id);
    Bank getBankByName(String name);
    List<Bank> getAllBanks();
    BankCurrencyResponse getAllBankCurrencies() throws SQLException, ClassNotFoundException;
}
