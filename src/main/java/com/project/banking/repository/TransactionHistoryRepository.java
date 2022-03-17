package com.project.banking.repository;

import com.project.banking.model.TransactionCategory;
import com.project.banking.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Integer> {

    @Query("FROM TransactionHistory WHERE transactionHistoryId = ?1")
    TransactionHistory findByTransactionHistoryId(Integer id);
}
