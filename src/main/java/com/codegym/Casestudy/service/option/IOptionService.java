package com.codegym.Casestudy.service.option;

import com.codegym.Casestudy.model.Option;

import java.util.Optional;

public interface IOptionService {
    Iterable<Option> findByOptionGroup(Long optionGroupId);

    Optional<Option> findByOptionId(Long id);
}
