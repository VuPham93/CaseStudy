package com.codegym.Casestudy.service.category;

import com.codegym.Casestudy.model.Category;

public interface ICategoryService {
    Iterable<Category> findAll();
    Category findCategoryByCategoryName(String name);
}
