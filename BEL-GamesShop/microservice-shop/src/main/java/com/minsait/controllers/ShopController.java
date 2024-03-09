package com.minsait.controllers;

import com.minsait.models.Shop;
import com.minsait.services.IShopService;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"api/v1/shop"})
public class ShopController {
    private static final Logger log = LoggerFactory.getLogger(ShopController.class);
    @Autowired
    IShopService shopService;

    @PostMapping({"/create"})
    @ResponseStatus(HttpStatus.CREATED)
    void saveShop(@RequestBody Shop shop) {
        this.shopService.save(shop);
    }

    @GetMapping
    public ResponseEntity<?> findAllShop() {
        return ResponseEntity.ok(this.shopService.findAll());
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.shopService.findById(id));
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(this.shopService.delleteById(id));
    }

    @GetMapping({"/searchUbication/{address}"})
    public ResponseEntity<?> searchUbication(@PathVariable String address) {
        try {
            return ResponseEntity.ok(this.shopService.queryForAddress(address));
        } catch (NoSuchElementException var3) {
            return ResponseEntity.notFound().build();
        }
    }
}
