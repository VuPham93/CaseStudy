package com.codegym.Casestudy.service.sku;

import com.codegym.Casestudy.model.Sku;

import java.util.Optional;

public interface ISkuService {
    Iterable<Sku> findAll();

    Optional<Sku> findById(Long id);

    Sku save(Sku sku);

    void delete(Sku sku);
}
