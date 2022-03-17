package com.project.banking.model;

import javax.persistence.*;

@Entity
@Table(name = "Transaccion", schema = "bankingapp")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTransaccion")
    private Integer transactionId;

    @Column(name = "IdTipoTransaccion")
    private Integer transactionTypeId;

    @Column(name = "Monto")
    private float amount;

    @Column(name = "IdCajero")
    private Integer atmId;

    @Column(name = "IdBanco")
    private Integer bankId;

    @Column(name = "IdCuentaBancaria")
    private Integer bankAccountId;

    @Column(name = "IdMoneda")
    private Integer currencyId;

    public Integer getTransactionId() {
        return transactionId;
    }

    public Integer getTransactionTypeId() {
        return transactionTypeId;
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

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public void setTransactionTypeId(Integer transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
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

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }
}
