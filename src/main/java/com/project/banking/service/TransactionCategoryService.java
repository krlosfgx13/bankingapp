package com.project.banking.service;

import com.project.banking.model.TransactionCategory;
import com.project.banking.response.ApiResponse;

import java.util.List;

public interface TransactionCategoryService {

    ApiResponse createTransactionCategory(TransactionCategory transactionCategory);
    ApiResponse updateTransactionCategory(Integer id, TransactionCategory transactionCategory);
    ApiResponse deleteTransactionCategory(Integer id);
    TransactionCategory getTransactionCategoryById(Integer id);
    List<TransactionCategory> getAllTransactionCategories();
}
