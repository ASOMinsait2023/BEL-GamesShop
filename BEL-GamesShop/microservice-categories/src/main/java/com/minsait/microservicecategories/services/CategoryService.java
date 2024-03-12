package com.minsait.microservicecategories.services;


import com.minsait.microservicecategories.models.Categories;
import com.minsait.microservicecategories.repositories.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    @Transactional (readOnly = true)
    public List<Categories> findAll() {
        return (List<Categories>) categoryRepository.findAll();
    }

    @Override
    @Transactional (readOnly = true)
    public Categories findById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void save(Categories category) {
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void deleteId(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateId(Categories category) {
        var categoryToUpdate = categoryRepository.findById(category.getIdCategory()).orElseThrow();
        categoryToUpdate.setNameCategory(category.getNameCategory());
        categoryToUpdate.setDescription(category.getDescription());
        categoryRepository.save(category);
    }
}
