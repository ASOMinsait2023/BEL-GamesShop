package com.minsait.microservicecategories.controllers;

import com.minsait.microservicecategories.models.Categories;
import com.minsait.microservicecategories.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    void saveCategory(@RequestBody Categories category){
        categoryService.save(category);
    }

    @GetMapping
    public ResponseEntity<?> findAllCategory() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
