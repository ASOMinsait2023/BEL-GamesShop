package com.minsait.microservicecategories.services;


import com.minsait.microservicecategories.clients.IVideoGamesClient;
import com.minsait.microservicecategories.models.Categories;
import com.minsait.microservicecategories.models.Platform;
import com.minsait.microservicecategories.repositories.ICategoryRepository;
import com.minsait.microservicecategories.repositories.IPlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private IPlatformRepository platformRepository;

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
    @Transactional (readOnly = true)
    public List<Categories> findByPlatformId(Long platformId) {
        return categoryRepository.findByPlatformId(platformId);
    }

    @Override
    @Transactional (readOnly = true)
    public List<Categories> findByVideoGameId(Long videoGameId) {
        return categoryRepository.findAllByVideoGameId(videoGameId);
    }

    @Override
    @Transactional
    public Categories save(Categories category) {
        return categoryRepository.save(category);
    }
    @Override
    @Transactional
    public boolean deleteId(Long id) {
        var categoryDelete = categoryRepository.findById(id);
        if (categoryDelete.isPresent()){
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void updateId(Categories category) {
        var categoryToUpdate = categoryRepository.findById(category.getId()).orElseThrow();
        categoryToUpdate.setNameCategory(category.getNameCategory());
        categoryToUpdate.setDescription(category.getDescription());
        categoryRepository.save(category);
    }

    /*@Override
    public CategoryByVideoGameDTO findCategoryByVideoGameId(Long id) {
        var category = categoryRepository.findById(id).orElseThrow();
        var platform = platformRepository.findById(category.getPlatform().getId()).orElseThrow();
        return CategoryByVideoGameDTO.builder()
                .id(category.getId())
                .name(category.getNameCategory())
                .description(category.getDescription())
                .releaseDate(category.getReleaseDate())
                .price(category.getPrice())
                .categoryDTOList(List.of(category))
                .build();
    }*/

   /* @Override
    @Transactional
    public VideogamesByCategoriesDTO findVideoGameByIdCategory(Long idCategory) {
        var category = categoryRepository.findById(idCategory).orElseThrow();
        var videogamesDTOList = videoGamesClient.findByIdCategory(idCategory);
        return VideogamesByCategoriesDTO.builder()
                .nameCategory(category.getNameCategory())
                .description(category.getDescription())
                .videoGameDTOList(videogamesDTOList)
                .build();
    }*/

}
