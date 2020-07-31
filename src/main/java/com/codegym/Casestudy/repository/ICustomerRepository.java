package com.codegym.Casestudy.repository;

import com.codegym.Casestudy.model.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByMail(String mail);
}
