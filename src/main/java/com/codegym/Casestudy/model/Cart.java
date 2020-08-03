package com.codegym.Casestudy.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    private Long userId;

    private double cartQuantity;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "cart_sku", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "sku_id"))
    private Collection<Sku> skus;

    public Cart() {
    }
    public Cart(Long userId) {
        this.userId = userId;
    }


    public double getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(double cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Collection<Sku> getSkus() {
        return skus;
    }

    public void setSkus(Collection<Sku> skus) {
        this.skus = skus;
    }
}
