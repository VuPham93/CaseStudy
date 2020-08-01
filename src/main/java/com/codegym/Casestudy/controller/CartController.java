package com.codegym.Casestudy.controller;

import com.codegym.Casestudy.model.Cart;
import com.codegym.Casestudy.model.Product;
import com.codegym.Casestudy.service.cart.ICartService;
import com.codegym.Casestudy.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpStatus;

import java.util.List;


@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ICartService cartService;
    @Autowired
    IProductService productService;

    @GetMapping("/addtocart/{id}")
    public ResponseEntity<Product> addToCart(@PathVariable("id") Long id) {
        Cart cart = cartService.findCartByUserId(1L);
        if (cart != null) {
            List<Product> products = (List<Product>) cart.getProducts();
            products.add(productService.findById(id).get());
            cart.setCartQuantity(1);
            cart.setProducts(products);
            cartService.save(cart);
        } else {
            cart.setUserId(1L);
            List<Product> products = (List<Product>) cart.getProducts();
            products.add(productService.findById(id).get());
            cart.setCartQuantity(1);
            cart.setProducts(products);
            cartService.save(cart);
        }
//        Iterable<Product> products = productService.findAll();
//        ModelAndView modelAndView = new ModelAndView("product/list");
//        modelAndView.addObject("products", products);
//        modelAndView.addObject("message", "add cart ok");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ModelAndView listCart() {
        Cart cart = cartService.findCartByUserId(1L);
        Iterable<Product> products = cart.getProducts();
        ModelAndView modelAndView = new ModelAndView("cart/cart");
        modelAndView.addObject("products", products);
        modelAndView.addObject("cart", cart);
        return modelAndView;
    }

    @GetMapping("/deleteproduct/{id}")
    public ModelAndView detleteProduct(@PathVariable("id") Long id) {
        Cart cart = cartService.findCartByUserId(1L);
        List<Product> products = (List<Product>) cart.getProducts();
        products.remove(productService.findById(id));
        cart.setProducts(products);
        cartService.save(cart);
        ModelAndView modelAndView = new ModelAndView("product/cart");
        modelAndView.addObject("products", products);
        modelAndView.addObject("cart", cart);
        return modelAndView;

    }
}
