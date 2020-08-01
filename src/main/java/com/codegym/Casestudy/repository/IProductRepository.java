package com.codegym.Casestudy.repository;

import com.codegym.Casestudy.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
    Iterable<Product> findByProductNameContains(String productName);
}
