package com.project.banking.response;

import java.util.List;

public class TransactionLogResponse extends ApiResponse{

    private List<TransactionLog> transactionLogList;

    public List<TransactionLog> getTransactionLogList() {
        return transactionLogList;
    }

    public void setTransactionLogList(List<TransactionLog> transactionLogList) {
        this.transactionLogList = transactionLogList;
    }
}
