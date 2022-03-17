package com.project.banking.repository;

import com.project.banking.model.Atm;
import com.project.banking.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {

    @Query("FROM Bank WHERE bankId = ?1")
    Bank findByBankId(Integer id);

    @Query("FROM Bank WHERE name = ?1")
    Bank findByBankName(String name);
}
