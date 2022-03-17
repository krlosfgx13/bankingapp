package com.project.banking.service.impl;

import com.project.banking.model.BankCurrency;
import com.project.banking.model.TransactionCategory;
import com.project.banking.repository.TransactionCategoryRepository;
import com.project.banking.response.ApiResponse;
import com.project.banking.service.TransactionCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionCategoryServiceImpl implements TransactionCategoryService {

    @Autowired
    private TransactionCategoryRepository repository;
    ApiResponse response = new ApiResponse();

    @Override
    public ApiResponse createTransactionCategory(TransactionCategory transactionCategory) {
        try{
            repository.save(transactionCategory);
            response.setMessage("Operation performed successfully.");
            response.setCode(200);
            response.setStatus("success");
            return response;
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public ApiResponse updateTransactionCategory(Integer id, TransactionCategory transactionCategory) {
        return null;
    }

    @Override
    public ApiResponse deleteTransactionCategory(Integer id) {
        return null;
    }

    @Override
    public TransactionCategory getTransactionCategoryById(Integer id) {
        try{
            return repository.findByTransactionCategoryId(id);
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public List<TransactionCategory> getAllTransactionCategories() {
        List<TransactionCategory> list = new ArrayList<>();
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
