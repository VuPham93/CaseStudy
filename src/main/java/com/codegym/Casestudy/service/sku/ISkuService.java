package com.codegym.Casestudy.service.sku;

import com.codegym.Casestudy.model.Sku;

import java.util.Optional;

public interface ISkuService {
    Iterable<Sku> findAll();

    Optional<Sku> findById(Long id);

    Sku findByProductIdAndAndOptions(Long productId, Long sizeOptionId, Long colorOptionId);

    Sku save(Sku sku);

    void delete(Long id);
}
