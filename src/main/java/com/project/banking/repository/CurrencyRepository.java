package com.project.banking.repository;

import com.project.banking.model.Bank;
import com.project.banking.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    @Query("FROM Currency WHERE currencyId = ?1")
    Currency findByCurrencyId(Integer id);
}

//3Pillar Global

