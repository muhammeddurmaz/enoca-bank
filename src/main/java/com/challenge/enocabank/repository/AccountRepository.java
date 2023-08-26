package com.challenge.enocabank.repository;

import com.challenge.enocabank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    List<Account> findAllByCustomer_Id(Long id);
}
