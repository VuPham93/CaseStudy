package com.codegym.Casestudy.repository;

import com.codegym.Casestudy.model.Sku;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISkuRepository extends CrudRepository<Sku, Long> {
//    Sku findByProductProductIdAndAndOptions(Long productId, Long sizeOptionId, Long colorOptionId);
}
