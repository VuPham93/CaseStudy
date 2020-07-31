package com.codegym.Casestudy.service.option;

import com.codegym.Casestudy.model.Option;

public interface IOptionService {
    Iterable<Option> findByOptionGroup(Long optionGroupId);
}
