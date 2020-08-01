package com.codegym.Casestudy.service.sku;

import com.codegym.Casestudy.model.Sku;

import java.util.Optional;

public interface ISkuService {
    Iterable<Sku> findAll();

    Optional<Sku> findById(Long id);

    Iterable<Sku> findByProductId(Long productId);

    Sku findByProductIdAndOptions(Long productId, Long sizeOption, Long colorOption);

    Sku save(Sku sku);

    void delete(Long id);
}
