package com.project.banking.model;

import javax.persistence.*;

@Entity
@Table(name = "bank_account", schema = "bankingapp")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_account_id")
    private Integer bankAccountId;

    @Column(name = "bank_id")
    private Integer bankId;

    @Column(name = "balance")
    private double balance;

    @Column(name = "person_id")
    private Integer personId;

    public Integer getBankAccountId() {
        return bankAccountId;
    }

    public Integer getBankId() {
        return bankId;
    }

    public double getBalance() {
        return balance;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
}
