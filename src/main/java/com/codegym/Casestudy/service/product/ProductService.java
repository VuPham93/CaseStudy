package com.codegym.Casestudy.service.product;

import com.codegym.Casestudy.model.Product;
import com.codegym.Casestudy.model.Sku;
import com.codegym.Casestudy.repository.IProductRepository;
import com.codegym.Casestudy.service.sku.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ISkuService skuService;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Iterable<Product> findByName(String productName) {
        return productRepository.findByProductNameContains(productName);
    }

    @Override
    public Product findProductBySkuId(Long skuId) {
        Sku sku = skuService.findById(skuId).get();
        return sku.getProduct();
    }

    @Override
    public Iterable<Product> findProductByCategory(Long categoryId) {
        return productRepository.findByCategoryCategoryId(categoryId);
    }

    @Override
    public Iterable<Product> findProductByOptionId(Long optionId) {
        Iterable<Sku> skuList = skuService.findByOptionId(optionId);
        List<Product> products = new ArrayList<>();
        for (Sku sku: skuList) {
            products.add(findProductBySkuId(sku.getSkuId()));
        }
        return products;
    }

}
