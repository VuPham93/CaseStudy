package com.codegym.Casestudy.service.customer;

import com.codegym.Casestudy.model.Customer;
import com.codegym.Casestudy.service.IService;

public interface ICustomerService extends IService<Customer>{
  Customer findByAccount(String account);
}
