package com.project.banking.repository;

import com.project.banking.model.Person;
import com.project.banking.model.TransactionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionCategoryRepository extends JpaRepository<TransactionCategory, Integer> {

    @Query("FROM TransactionCategory WHERE transactionCategoryId = ?1")
    TransactionCategory findByTransactionCategoryId(Integer id);
}
