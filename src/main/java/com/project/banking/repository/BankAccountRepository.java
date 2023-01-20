package com.project.banking.repository;

import com.project.banking.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    @Query("FROM BankAccount WHERE bankAccountId = ?1")
    BankAccount findByAccountId(int id);

    @Query("FROM BankAccount WHERE person_id = ?1")
    BankAccount findByPersonId(Integer id);

    //select cb.idcuentabancaria, cb.dpi, u.nombreusuario, cb.saldo from cuentabancaria cb join usuario u on cb.dpi = u.dpi;
    //@Query("select cb.bankAccountId, cb.dpi, u.userName, cb.balance from BankAccount ba join User u on cb.dpi = u.dpi;")
    //BankAccount findByUserName(String userName);

}
