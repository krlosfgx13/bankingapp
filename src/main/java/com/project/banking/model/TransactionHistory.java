package com.project.banking.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "HistorialTransacciones", schema = "bankingapp")
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdHistorialTransacciones")
    private Integer transactionHistoryId;

    @Column(name = "FechaTransaccion")
    private Timestamp transactionDate;

    @Column(name = "IdTransaccion")
    private Integer transactionId;

    public Integer getTransactionHistoryId() {
        return transactionHistoryId;
    }

    public Timestamp getTimestamp() {
        return transactionDate;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionHistoryId(Integer transactionHistoryId) {
        this.transactionHistoryId = transactionHistoryId;
    }

    public void setTimestamp(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }
}
