package com.codegym.Casestudy.service.product;

import com.codegym.Casestudy.model.Product;

import java.util.Optional;

public interface IProductService {
    Iterable<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    void delete(Long id);
}
