package com.codegym.Casestudy.service.sku;

import com.codegym.Casestudy.model.Option;
import com.codegym.Casestudy.model.Sku;
import com.codegym.Casestudy.repository.ISkuRepository;
import com.codegym.Casestudy.service.option.IOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class SkuService implements ISkuService {
    @Autowired
    private ISkuRepository skuRepository;

    @Autowired
    private IOptionService optionService;

    @Override
    public Sku save(Sku sku) {
        return skuRepository.save(sku);
    }

    @Override
    public void delete(Long id) {
        skuRepository.deleteById(id);
    }

    @Override
    public Iterable<Sku> findAll() {
        return skuRepository.findAll();
    }

    @Override
    public Optional<Sku> findById(Long id) {
        return skuRepository.findById(id);
    }

    @Override
    public Iterable<Sku> findByProductId(Long productId) {
        return skuRepository.findByProductProductId(productId);
    }

    @Override
    public Sku findByProductIdAndOptions(Long productId, Long sizeOptionId, Long colorOptionId) {
       Iterable<Sku> skuList = skuRepository.findByProductProductId(productId);
       Option sizeOption = optionService.findByOptionId(sizeOptionId).get();
       Option colorOption = optionService.findByOptionId(colorOptionId).get();

       for (Sku sku: skuList) {
           Collection<Option> tempOptions = sku.getOptions();
           List<Long> optionsId = new ArrayList<>();

           for (Option option: tempOptions) {
               Long optionId = option.getOptionId();
               optionsId.add(optionId);
           }

           if (optionsId.contains(sizeOptionId) && optionsId.contains(colorOptionId)) {
               return sku;
           }
        }
       return null;
    }

    @Override
    public Iterable<Sku> findByOptionId(Long optionId) {
        Iterable<Sku> skuList = skuRepository.findAll();
        List<Sku> foundSkuList = new ArrayList<>();

        for (Sku sku : skuList) {
            Collection<Option> optionList = sku.getOptions();

            if (optionList.contains(optionService.findByOptionId(optionId).get())) {
                foundSkuList.add(sku);
            }
        }
        return foundSkuList;
    }
}
