package com.codegym.Casestudy.service;

import com.codegym.Casestudy.exception.NotFoundException;

import java.util.Optional;

public interface IService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id) throws NotFoundException;

    void save(T model);

    void remove(Long id);
}