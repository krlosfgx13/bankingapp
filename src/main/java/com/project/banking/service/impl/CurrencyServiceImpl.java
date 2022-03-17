package com.project.banking.service.impl;

import com.project.banking.model.BankCurrency;
import com.project.banking.model.Currency;
import com.project.banking.repository.CurrencyRepository;
import com.project.banking.response.ApiResponse;
import com.project.banking.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository repository;
    ApiResponse response = new ApiResponse();

    @Override
    public ApiResponse createCurrency(Currency currency) {
        try{
            repository.save(currency);
            response.setMessage("Operation performed successfully.");
            response.setCode(200);
            response.setStatus("success");
            return response;
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public ApiResponse updateCurrency(Integer id, Currency currency) {
        return null;
    }

    @Override
    public ApiResponse deleteCurrency(Integer id) {
        return null;
    }

    @Override
    public Currency getCurrencyById(Integer id) {
        try{
            return repository.findByCurrencyId(id);
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public List<Currency> getAllCurrencies() {
        List<Currency> list = new ArrayList<>();
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
