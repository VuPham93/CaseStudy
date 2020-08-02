package com.codegym.Casestudy.service.category;

import com.codegym.Casestudy.model.Category;
import com.codegym.Casestudy.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryByCategoryName(String name) {
        return categoryRepository.findCategoryByCategoryName(name);
    }
}
