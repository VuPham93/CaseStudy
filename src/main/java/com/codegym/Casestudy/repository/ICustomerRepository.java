package com.codegym.Casestudy.repository;

import com.codegym.Casestudy.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByAccount(String account);
}
