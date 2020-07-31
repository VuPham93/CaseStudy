package com.codegym.Casestudy.service.customer;

import com.codegym.Casestudy.exception.UserAlreadyExistException;
import com.codegym.Casestudy.model.user.Customer;
import com.codegym.Casestudy.service.IService;

public interface ICustomerService extends IService<Customer>{
  Customer findByMail(String mail);
  boolean emailExists(String email);
  void registerNewUserAccount(Customer customer)
          throws UserAlreadyExistException;
}
