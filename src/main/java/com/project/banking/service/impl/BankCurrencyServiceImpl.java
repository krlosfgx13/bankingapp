package com.project.banking.service.impl;

import com.project.banking.model.BankAccount;
import com.project.banking.model.BankCurrency;
import com.project.banking.repository.BankCurrencyRepository;
import com.project.banking.response.ApiResponse;
import com.project.banking.service.BankCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankCurrencyServiceImpl implements BankCurrencyService {

    @Autowired
    private BankCurrencyRepository repository;
    ApiResponse response = new ApiResponse();

    @Override
    public ApiResponse createBankCurrency(BankCurrency bankCurrency) {
        try{
            repository.save(bankCurrency);
            response.setMessage("Operation performed successfully.");
            response.setCode(200);
            response.setStatus("success");
            return response;
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public ApiResponse updateBankCurrency(Integer id, BankCurrency bankCurrency) {
        return null;
    }

    @Override
    public ApiResponse deleteBankCurrency(Integer id) {
        return null;
    }

    @Override
    public BankCurrency getBankCurrencyById(Integer id) {
        try{
            return repository.findByBankCurrencyId(id);
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public BankCurrency getByBankAndCurrency(Integer bankId, Integer currencyId) {
        try{
            return repository.findByBankAndCurrency(bankId, currencyId);
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public List<BankCurrency> getAllBankCurrencies() {
        List<BankCurrency> list = new ArrayList<>();
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
