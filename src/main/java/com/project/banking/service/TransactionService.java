package com.project.banking.service;

import com.project.banking.model.BankAccount;
import com.project.banking.response.ApiResponse;
import com.project.banking.response.BalanceInquiryResponse;
import com.project.banking.response.TransactionLogResponse;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public interface TransactionService {

    ApiResponse depositMoney(Integer bankAccountId, Integer currencyId, float amount);
    BalanceInquiryResponse balanceInquiry(Integer atmId, String userName, String password) throws NoSuchAlgorithmException, InvalidKeySpecException;
    ApiResponse withdrawMoney(Integer atmId, String userName, String password, float amount) throws NoSuchAlgorithmException, InvalidKeySpecException;
    ApiResponse depositMoneyWithPayCheck(Integer atmId, String userName, String password, String bankName,
                                         Integer currencyId, float amount, Integer bankAccountId) throws NoSuchAlgorithmException, InvalidKeySpecException;
    ApiResponse monetaryInvestment(Integer bankId, Integer currencyId, float amount);
    ApiResponse rechargeAtm(Integer atmId, Integer currencyId, float amount);
    TransactionLogResponse getTransactionLog() throws SQLException, ClassNotFoundException;
}
