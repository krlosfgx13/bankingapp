package com.project.banking.repository;

import com.project.banking.model.TransactionHistory;
import com.project.banking.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Integer> {

    @Query("FROM TransactionType WHERE transactionTypeId = ?1")
    TransactionType findByTransactionHistoryId(Integer id);
}
