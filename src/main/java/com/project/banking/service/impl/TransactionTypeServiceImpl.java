package com.project.banking.service.impl;

import com.project.banking.model.BankCurrency;
import com.project.banking.model.TransactionType;
import com.project.banking.repository.TransactionTypeRepository;
import com.project.banking.response.ApiResponse;
import com.project.banking.service.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {

    @Autowired
    private TransactionTypeRepository repository;
    ApiResponse response = new ApiResponse();

    @Override
    public ApiResponse createTransactionType(TransactionType transactionType) {
        try{
            repository.save(transactionType);
            response.setMessage("Operation performed successfully.");
            response.setCode(200);
            response.setStatus("success");
            return response;
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public ApiResponse updateTransactionType(Integer id, TransactionType transactionCategory) {
        return null;
    }

    @Override
    public ApiResponse deleteTransactionType(Integer id) {
        return null;
    }

    @Override
    public TransactionType getTransactionTypeById(Integer id) {
        try{
            return repository.findByTransactionHistoryId(id);
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public List<TransactionType> getAllTransactionTypes() {
        List<TransactionType> list = new ArrayList<>();
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
