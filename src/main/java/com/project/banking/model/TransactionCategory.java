package com.project.banking.model;

import com.project.banking.service.TransactionTypeService;

import javax.persistence.*;

@Entity
@Table(name = "CategoriaTransaccion", schema = "bankingapp")
public class TransactionCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCategoriaTransaccion")
    private Integer transactionCategoryId;

    @Enumerated(EnumType.STRING)
    @Column(name = "Descripcion")
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
