package com.project.banking.response;

import com.project.banking.model.BankAccount;

public class BalanceInquiryResponse extends ApiResponse{

    private BankAccount bankAccount;

    public BalanceInquiryResponse(Integer code, String message, String status) {
        super(code, message, status);
    }

    public BalanceInquiryResponse(){

    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
