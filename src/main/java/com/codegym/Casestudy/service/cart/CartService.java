package com.codegym.Casestudy.service.cart;

import com.codegym.Casestudy.model.Cart;
import com.codegym.Casestudy.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService {
    @Autowired
    CartRepository cartRepository;

    @Override
    public Iterable<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.getOne(id);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);

    }

    @Override
    public void remove(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Cart findCartByUserId(Long id) {
        return cartRepository.findCartByUserId(id);
    }


//    @Override
//    public Cart findByUserId(Long id) {
//        return cartRepository.findByUserId(id);
//    }
}
