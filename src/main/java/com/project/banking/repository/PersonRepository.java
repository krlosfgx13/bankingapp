package com.project.banking.repository;

import com.project.banking.model.Currency;
import com.project.banking.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("FROM Person WHERE dpi = ?1")
    Person findByPersonId(Long id);
}
