package com.project.banking.model;

import javax.persistence.*;

@Entity
@Table(name = "transaction_type")
public class TransactionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_type_id")
    private Integer transactionTypeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "description")
    private TransactionTypeEnum description;

    @Column(name = "transaction_category_id")
    private Integer transactionCategoryId;

    public Integer getTransactionTypeId() {
        return transactionTypeId;
    }

    public TransactionTypeEnum getDescription() {
        return description;
    }

    public Integer getTransactionCategoryId() {
        return transactionCategoryId;
    }

    public void setTransactionTypeId(Integer transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public void setDescription(TransactionTypeEnum description) {
        this.description = description;
    }

    public void setTransactionCategoryId(Integer transactionCategoryId) {
        this.transactionCategoryId = transactionCategoryId;
    }
}
