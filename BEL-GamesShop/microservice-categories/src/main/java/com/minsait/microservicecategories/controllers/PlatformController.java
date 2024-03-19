package com.minsait.microservicecategories.controllers;

import com.minsait.microservicecategories.models.Categories;
import com.minsait.microservicecategories.models.Platform;
import com.minsait.microservicecategories.models.dtos.CategoryDTO;
import com.minsait.microservicecategories.models.dtos.PlatformDTO;
import com.minsait.microservicecategories.services.IPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/platforms")
public class PlatformController {
    @Autowired
    private IPlatformService platformService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    void savePlatform(@RequestBody Platform platform){
        try {
            platformService.save(platform);
        } catch (Exception e) {
            ResponseEntity.notFound().build();
        }
    }

    private PlatformDTO mapPlatformToDTO(Platform platform) {
        PlatformDTO platformDTO = new PlatformDTO();
        platformDTO.setId(platform.getId());
        platformDTO.setName(platform.getNamePlatform());
        platformDTO.setPublisher(platform.getPublisher());
        platformDTO.setGeneration(platform.getGeneration());
        platformDTO.setCategories(mapCategoriesToDTOs(platform.getCategories()));
        return platformDTO;
    }

    private Set<CategoryDTO> mapCategoriesToDTOs(Set<Categories> categories) {
        Set<CategoryDTO> categoryDTOs = new HashSet<>();
        for (Categories category : categories) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setName(category.getNameCategory());
            categoryDTO.setDescription(category.getDescription());
            categoryDTOs.add(categoryDTO);
        }
        return categoryDTOs;
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(platformService.findAll());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatformDTO> findById(@PathVariable Long id) {
        Platform platform = platformService.findById(id);
        if (platform == null) {
            return ResponseEntity.notFound().build();
        }
        PlatformDTO platformDTO = mapPlatformToDTO(platform);
        return ResponseEntity.ok(platformDTO);
       /* try {
            return ResponseEntity.ok(platformService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }*/
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlatform(@PathVariable Long id) {
        try {
            platformService.deleteId(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlatform(@PathVariable Long id, @RequestBody Platform platform) {

        try {
            var platformToUpdate = platformService.findById(id);
            platformToUpdate.setNamePlatform(platform.getNamePlatform());
            platformToUpdate.setPublisher(platform.getPublisher());
            platformToUpdate.setGeneration(platform.getGeneration());
            platformService.updateId(platformToUpdate);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
