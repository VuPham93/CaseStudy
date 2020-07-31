package com.codegym.Casestudy.service.sku;

import com.codegym.Casestudy.model.Sku;
import com.codegym.Casestudy.repository.ISkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SkuService implements ISkuService {
    @Autowired
    private ISkuRepository skuRepository;

    @Override
    public Iterable<Sku> findAll() {
        return skuRepository.findAll();
    }

    @Override
    public Optional<Sku> findById(Long id) {
        return skuRepository.findById(id);
    }

    @Override
    public Sku save(Sku sku) {
        return skuRepository.save(sku);
    }

    @Override
    public void delete(Long id) {
        skuRepository.deleteById(id);
    }
}
