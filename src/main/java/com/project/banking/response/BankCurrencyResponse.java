package com.project.banking.response;

import java.util.List;

public class BankCurrencyResponse extends ApiResponse{

    private List<BankCurrencyData> bankCurrencyDataList;

    public BankCurrencyResponse(){

    }

    public BankCurrencyResponse(Integer code, String message, String status) {
        super(code, message, status);
    }

    public List<BankCurrencyData> getBankCurrencyDataList() {
        return bankCurrencyDataList;
    }

    public void setBankCurrencyDataList(List<BankCurrencyData> bankCurrencyDataList) {
        this.bankCurrencyDataList = bankCurrencyDataList;
    }
}
