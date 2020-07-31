package com.codegym.Casestudy.service.cart;

import com.codegym.Casestudy.model.Cart;


public interface ICartService {
    Iterable<Cart> findAll();

    Cart findById(Long id);

    Cart save(Cart cart);

    void remove(Long id);

    //    Cart findByUserId(Long id);
    Cart findCartByUserId(Long id);
}
