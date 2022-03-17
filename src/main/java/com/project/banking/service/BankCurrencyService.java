package com.project.banking.service;

import com.project.banking.model.BankCurrency;
import com.project.banking.response.ApiResponse;

import java.util.List;

public interface BankCurrencyService {

    ApiResponse createBankCurrency(BankCurrency bankCurrency);
    ApiResponse updateBankCurrency(Integer id, BankCurrency bankCurrency);
    ApiResponse deleteBankCurrency(Integer id);
    BankCurrency getBankCurrencyById(Integer id);
    BankCurrency getByBankAndCurrency(Integer bankId, Integer currencyId);
    List<BankCurrency> getAllBankCurrencies();
}
