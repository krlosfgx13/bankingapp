package com.project.banking.model;

import javax.persistence.*;

@Entity
@Table(name = "currency_bank", schema = "bankingapp")
public class BankCurrency {

    @Id
    @Column(name = "currency_bank_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bankCurrencyId;

    @Column(name = "bank_id")
    private Integer bankId;

    @Column(name = "currency_id")
    private Integer currencyId;

    public Integer getBankCurrencyId() {
        return bankCurrencyId;
    }

    public Integer getBankId() {
        return bankId;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setBankCurrencyId(Integer bankCurrencyId) {
        this.bankCurrencyId = bankCurrencyId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }
}
