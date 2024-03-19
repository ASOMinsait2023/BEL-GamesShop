package com.minsait.microservicecategories.services;

import com.minsait.microservicecategories.models.Categories;
import com.minsait.microservicecategories.models.dtos.CategoryByVideoGameDTO;

import java.util.List;

public interface ICategoryService {

    List<Categories> findAll();
    Categories findById(Long id);
    Categories save(Categories category);
    boolean deleteId(Long id);
    void updateId(Categories category);
    //CategoryByVideoGameDTO findCategoryByVideoGameId(Long id);
    List<Categories> findByPlatformId(Long platformId);
    List<Categories> findByVideoGameId(Long videoGameId);

}