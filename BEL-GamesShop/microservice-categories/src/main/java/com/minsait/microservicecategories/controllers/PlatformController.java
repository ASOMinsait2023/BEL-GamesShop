package com.minsait.microservicecategories.controllers;

import com.minsait.microservicecategories.models.Platform;
import com.minsait.microservicecategories.services.IPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<?> findAllPlatform() {
        try {
            return ResponseEntity.ok(platformService.findAll());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(platformService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
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
