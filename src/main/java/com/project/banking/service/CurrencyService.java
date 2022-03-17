package com.project.banking.service;

import com.project.banking.model.Currency;
import com.project.banking.response.ApiResponse;

import java.util.List;

public interface CurrencyService {

    ApiResponse createCurrency(Currency currency);
    ApiResponse updateCurrency(Integer id, Currency currency);
    ApiResponse deleteCurrency(Integer id);
    Currency getCurrencyById(Integer id);
    List<Currency> getAllCurrencies();
}
