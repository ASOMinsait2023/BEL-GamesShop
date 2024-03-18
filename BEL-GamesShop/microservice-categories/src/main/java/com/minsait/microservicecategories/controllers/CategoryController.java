package com.minsait.microservicecategories.controllers;

import com.minsait.microservicecategories.models.Categories;
import com.minsait.microservicecategories.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    void saveCategory(@RequestBody Categories category){
        try {
            categoryService.save(category);
        } catch (Exception e) {
            ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> findAllCategory() {
        try {
            return ResponseEntity.ok(categoryService.findAll());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(categoryService.findById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteId(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Categories category) {
        try {
            var categoryToUpdate = categoryService.findById(id);
            categoryToUpdate.setNameCategory(category.getNameCategory());
            categoryToUpdate.setDescription(category.getDescription());
            categoryService.updateId(categoryToUpdate);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
