package com.project.banking.repository;

import com.project.banking.model.Atm;
import com.project.banking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AtmRepository extends JpaRepository<Atm, Integer> {

    @Query("FROM Atm WHERE atmId = ?1")
    Atm findByAtmId(Integer id);
}
