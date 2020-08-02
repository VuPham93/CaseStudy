package com.codegym.Casestudy.repository;

import com.codegym.Casestudy.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepository extends JpaRepository<Cart, Long> {
    //Cart findByUserId(Long id);
    Cart findCartByUserId(Long id);
}
