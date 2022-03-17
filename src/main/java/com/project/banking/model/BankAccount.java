package com.project.banking.model;

import javax.persistence.*;

@Entity
@Table(name = "CuentaBancaria", schema = "bankingapp")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCuentaBancaria")
    private Integer bankAccountId;

    @Column(name = "IdBanco")
    private Integer bankId;

    @Column(name = "Saldo")
    private float balance;

    @Column(name = "Dpi")
    private Long dpi;

    public Integer getBankAccountId() {
        return bankAccountId;
    }

    public Integer getBankId() {
        return bankId;
    }

    public float getBalance() {
        return balance;
    }

    public Long getDpi() {
        return dpi;
    }

    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setDpi(Long dpi) {
        this.dpi = dpi;
    }
}
