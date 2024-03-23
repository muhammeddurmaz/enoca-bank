package com.challenge.enocabank.repository;

import com.challenge.enocabank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

//    @Query("select * from customer left join account on account.customer_id = customer.id where account.id is null")
//    List<Customer> findAllByNullAccount();
}
