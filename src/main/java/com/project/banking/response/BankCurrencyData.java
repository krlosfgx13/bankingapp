package com.project.banking.response;

public class BankCurrencyData {

    private Integer bankId;
    private String bankName;
    private String bankAddress;
    private String currencyDescription;

    public Integer getBankId() {
        return bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public String getCurrencyDescription() {
        return currencyDescription;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public void setCurrencyDescription(String currencyDescription) {
        this.currencyDescription = currencyDescription;
    }
}
