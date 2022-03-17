package com.project.banking.response;

import java.sql.Timestamp;

public class TransactionLog {

    private Integer TransactionId;
    private String transactionDescription;
    private float amount;
    private Integer atmId;
    private Integer bankId;
    private Integer bankAccountId;
    private String userName;
    private String currencyDescription;
    private Timestamp transactionDate;
    private String transactionCategory;

    public Integer getTransactionId() {
        return TransactionId;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public float getAmount() {
        return amount;
    }

    public Integer getAtmId() {
        return atmId;
    }

    public Integer getBankId() {
        return bankId;
    }

    public Integer getBankAccountId() {
        return bankAccountId;
    }

    public String getUserName() {
        return userName;
    }

    public String getCurrencyDescription() {
        return currencyDescription;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionId(Integer transactionId) {
        TransactionId = transactionId;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setAtmId(Integer atmId) {
        this.atmId = atmId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCurrencyDescription(String currencyDescription) {
        this.currencyDescription = currencyDescription;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setTransactionCategory(String transactionCategory) {
        this.transactionCategory = transactionCategory;
    }
}

