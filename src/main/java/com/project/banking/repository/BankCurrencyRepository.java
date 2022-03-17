package com.project.banking.repository;

import com.project.banking.model.Atm;
import com.project.banking.model.BankCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BankCurrencyRepository extends JpaRepository<BankCurrency, Integer> {

    @Query("FROM BankCurrency WHERE bankCurrencyId = ?1")
    BankCurrency findByBankCurrencyId(Integer id);

    @Query("FROM BankCurrency WHERE bankId = ?1 AND currencyId = ?2")
    BankCurrency findByBankAndCurrency(Integer bankId, Integer currencyId);
}
