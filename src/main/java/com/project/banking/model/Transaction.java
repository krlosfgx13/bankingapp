package com.project.banking.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "banking_transaction", schema = "bankingapp")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banking_transaction_id")
    private Integer transactionId;

    @Column(name = "transaction_type_id")
    private Integer transactionTypeId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "atm_id")
    private Integer atmId;

    @Column(name = "bank_id")
    private Integer bankId;

    @Column(name = "bank_account_id")
    private Integer bankAccountId;

    @Column(name = "currency_id")
    private Integer currencyId;

    @Column(name = "transaction_date")
    private Timestamp transactionDate;

    public Integer getTransactionId() {
        return transactionId;
    }

    public Integer getTransactionTypeId() {
        return transactionTypeId;
    }

    public double getAmount() {
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

    public Integer getCurrencyId() {
        return currencyId;
    }
    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public void setTransactionTypeId(Integer transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public void setAmount(double amount) {
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

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }
}
