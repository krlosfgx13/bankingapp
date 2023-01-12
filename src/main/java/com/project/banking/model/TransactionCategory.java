package com.project.banking.model;

import com.project.banking.service.TransactionTypeService;

import javax.persistence.*;

@Entity
@Table(name = "transaction_category", schema = "bankingapp")
public class TransactionCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_category_id")
    private Integer transactionCategoryId;

    @Enumerated(EnumType.STRING)
    @Column(name = "description")
    private TransactionCategoryEnum description;

    public Integer getTransactionCategoryId() {
        return transactionCategoryId;
    }

    public TransactionCategoryEnum getDescription() {
        return description;
    }

    public void setTransactionCategoryId(Integer transactionCategoryId) {
        this.transactionCategoryId = transactionCategoryId;
    }

    public void setDescription(TransactionCategoryEnum description) {
        this.description = description;
    }
}
