package com.codegym.Casestudy.service.product;

import com.codegym.Casestudy.model.Product;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface IProductService {
    Product save(Product product);

    void delete(Long id);

    Iterable<Product> findAll();

    Optional<Product> findById(Long id);

    Iterable<Product> findByName(String productName);

    Product findProductBySkuId(Long skuId);

    Iterable<Product> findProductByCategory(Long categoryId);

    Iterable<Product> findProductByOptionId(Long optionId);
}
