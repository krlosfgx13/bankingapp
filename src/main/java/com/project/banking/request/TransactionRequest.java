package com.project.banking.request;

public class TransactionRequest {

    private Integer bankAccountId;
    private Integer atmId;
    private Integer bankId;
    private Integer currencyId;
    private float amount;
    private String userName;
    private String password;
    private String bankName;

    public Integer getBankAccountId() {
        return bankAccountId;
    }

    public Integer getAtmId() {
        return atmId;
    }

    public Integer getBankId() {
        return bankId;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public float getAmount() {
        return amount;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public void setAtmId(Integer atmId) {
        this.atmId = atmId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
