package com.codegym.Casestudy.service.product;

import com.codegym.Casestudy.model.Product;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface IProductService {
    Iterable<Product> findAll();

    Optional<Product> findById(Long id);

    Iterable<Product> findByName(String productName);

    Product findProductBySkuId(Long skuId);

    Product save(Product product);

    void delete(Long id);
}
