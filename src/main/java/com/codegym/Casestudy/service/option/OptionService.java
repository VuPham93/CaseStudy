package com.codegym.Casestudy.service.option;

import com.codegym.Casestudy.model.Option;
import com.codegym.Casestudy.repository.IOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionService implements IOptionService {
    @Autowired
    private IOptionRepository optionRepository;

    @Override
    public Iterable<Option> findByOptionGroup(Long optionGroupId) {
        return  optionRepository.findByOptionGroup_OptionGroupId(optionGroupId);
    }
}
