package com.project.banking.service;

import com.project.banking.model.TransactionHistory;
import com.project.banking.response.ApiResponse;

import java.util.List;

public interface TransactionHistoryService {

    ApiResponse createTransactionHistory(TransactionHistory transactionHistory);
    ApiResponse updateTransactionHistory(Integer id, TransactionHistory transactionHistory);
    ApiResponse deleteTransactionHistory(Integer id);
    TransactionHistory getTransactionHistoryById(Integer id);
    List<TransactionHistory> getAllTransactionHistory();
}
