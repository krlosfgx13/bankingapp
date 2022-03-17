package com.project.banking.service;

import com.project.banking.model.TransactionType;
import com.project.banking.response.ApiResponse;

import java.util.List;

public interface TransactionTypeService {

    ApiResponse createTransactionType(TransactionType transactionType);
    ApiResponse updateTransactionType(Integer id, TransactionType transactionCategory);
    ApiResponse deleteTransactionType(Integer id);
    TransactionType getTransactionTypeById(Integer id);
    List<TransactionType> getAllTransactionTypes();
}
