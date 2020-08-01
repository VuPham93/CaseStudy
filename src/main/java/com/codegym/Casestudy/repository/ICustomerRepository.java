package com.codegym.Casestudy.repository;

import com.codegym.Casestudy.model.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByMail(String mail);
}
