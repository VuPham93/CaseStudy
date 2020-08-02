package com.codegym.Casestudy.controller;

import com.codegym.Casestudy.exception.UserAlreadyExistException;
import com.codegym.Casestudy.model.user.Customer;
import com.codegym.Casestudy.service.customer.ICustomerService;
import com.codegym.Casestudy.service.product.IProductService;
import com.codegym.Casestudy.service.sku.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    IProductService productService;

    @Autowired
    ISkuService skuService;

    @Autowired
    ICustomerService customerService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/home/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("productList", productService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView formCreate() {
        ModelAndView modelAndView = new ModelAndView("signup");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveCustomer(@ModelAttribute("customer") @Valid Customer customer, HttpServletRequest request, Errors errors) {
        try {
            customerService.registerNewUserAccount(customer);

        } catch (UserAlreadyExistException uaeEx) {
            ModelAndView mav = new ModelAndView("signup");
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }
            return new ModelAndView("signup", "customer", customer);
    }
}

