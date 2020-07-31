package com.codegym.Casestudy.repository;

import com.codegym.Casestudy.model.Option;
import org.springframework.data.repository.CrudRepository;

public interface IOptionRepository extends CrudRepository<Option, Long> {
    Iterable<Option> findByOptionGroup_OptionGroupId(Long id);
}
