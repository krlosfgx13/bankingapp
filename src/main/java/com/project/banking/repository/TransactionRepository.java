package com.project.banking.repository;

import com.project.banking.model.BankAccount;
import com.project.banking.model.Transaction;
import com.project.banking.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query("SELECT bankAccountId FROM BankAccount WHERE bankAccountId = ?1")
    int findAccoutById(int bankAccountId);

    @Query("FROM Transaction WHERE transactionId = ?1")
    Transaction findByTransactionId(Integer id);

}
