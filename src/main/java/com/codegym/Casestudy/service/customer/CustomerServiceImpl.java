package com.codegym.Casestudy.service.customer;

import com.codegym.Casestudy.exception.UserAlreadyExistException;
import com.codegym.Casestudy.model.user.Customer;
import com.codegym.Casestudy.repository.ICustomerRepository;
import com.codegym.Casestudy.service.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl implements ICustomerService, UserDetailsService {

    @Autowired
    ICustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    IRoleService roleService;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id){
        return customerRepository.findById(id);
    }

    @Override
    public void save(Customer model) {
        customerRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer findByMail(String mail) {
        return customerRepository.findByMail(mail);
    }

    @Override
    public boolean emailExists(String mail) {
        return customerRepository.findByMail(mail) != null;
    }

    @Override
    public void registerNewUserAccount(Customer customer) throws UserAlreadyExistException {
        if (emailExists(customer.getMail())==true){
            throw new UserAlreadyExistException(
                    "There is an account with that email address:"
                            + customer.getMail());
        }
            Customer newCutomer = new Customer();
        newCutomer.setName(customer.getName());
        newCutomer.setMail(customer.getMail());
        newCutomer.setPassword(passwordEncoder.encode(customer.getPassword()));
        newCutomer.setPassword(passwordEncoder.encode(customer.getMatchingPassword()));
        newCutomer.setAddress(customer.getAddress());
        newCutomer.setPhoneNumber(customer.getPhoneNumber());
        newCutomer.setRole(roleService.findByName("ROLE_USER"));
        customerRepository.save(newCutomer);
    }

    @Override
    public void update(Customer model) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = this.findByMail(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(customer.getRole());

        UserDetails userDetails = new User(customer.getMail(),
                customer.getPassword(),
                authorities);
        return userDetails;
    }

}
