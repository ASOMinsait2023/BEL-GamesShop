package com.minsait.microservicecategories.services;

import com.minsait.microservicecategories.models.Categories;

import java.util.List;

public interface ICategoryService {

    List<Categories> findAll();
    Categories findById(Long id);
    void save(Categories category);
    void delete(Long id);


}