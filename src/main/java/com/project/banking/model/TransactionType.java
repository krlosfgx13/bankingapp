package com.project.banking.model;

import javax.persistence.*;

@Entity
@Table(name = "TipoTransaccion")
public class TransactionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTipoTransaccion")
    private Integer transactionTypeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "Descripcion")
    private TransactionTypeEnum description;

    @Column(name = "IdCategoriaTransaccion")
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
