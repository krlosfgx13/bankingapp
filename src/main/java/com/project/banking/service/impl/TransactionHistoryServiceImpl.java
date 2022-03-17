package com.project.banking.service.impl;

import com.project.banking.model.BankCurrency;
import com.project.banking.model.TransactionHistory;
import com.project.banking.repository.TransactionHistoryRepository;
import com.project.banking.response.ApiResponse;
import com.project.banking.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    @Autowired
    private TransactionHistoryRepository repository;
    ApiResponse response = new ApiResponse();

    @Override
    public ApiResponse createTransactionHistory(TransactionHistory transactionHistory) {
        try{
            repository.save(transactionHistory);
            response.setMessage("Operation performed successfully.");
            response.setCode(200);
            response.setStatus("success");
            return response;
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public ApiResponse updateTransactionHistory(Integer id, TransactionHistory transactionHistory) {
        return null;
    }

    @Override
    public ApiResponse deleteTransactionHistory(Integer id) {
        return null;
    }

    @Override
    public TransactionHistory getTransactionHistoryById(Integer id) {
        try{
            return repository.findByTransactionHistoryId(id);
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public List<TransactionHistory> getAllTransactionHistory() {
        List<TransactionHistory> list = new ArrayList<>();
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
