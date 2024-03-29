package com.minsait.controllers;

import com.minsait.models.Promotion;
import com.minsait.services.IPromotionServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/promotion")
@Slf4j
public class PromotionController {

    @Autowired
    IPromotionServices promotionServices;

    @GetMapping
    ResponseEntity<?> findAllPromotion(){

        List<Promotion> promotions =  promotionServices.findAll();

        promotions.forEach(promotion -> {
            BigDecimal originalPrice = promotion.getVideogame().getPrice();
            BigDecimal discountedPrice = promotion.calculateDiscountedPrice(originalPrice);
            promotion.getVideogame().setPrice(discountedPrice);
        });
        return ResponseEntity.ok(promotions);
    }

    @GetMapping("{id}")
    ResponseEntity<?> findByIdPromotion(@PathVariable Long id){
        try {
            var promotion = promotionServices.findById(id);
            BigDecimal originalPrice = promotion.getVideogame().getPrice();
            BigDecimal discountedPrice = promotion.calculateDiscountedPrice(originalPrice);
            promotion.getVideogame().setPrice(discountedPrice);
            return ResponseEntity.ok(promotion);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/create")
    ResponseEntity<?> save(@RequestBody Promotion promotion) {

        var response = new HashMap<String, Object>();
        response.put("fecha", LocalDate.now().toString());
        response.put("peticion",promotion );

        try {
            promotionServices.save(promotion);
            response.put("status", "CREATED");
            response.put("mensaje", "Se ha creado un nuevo juego");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("{id}")
    ResponseEntity<?>update(@RequestBody Promotion promotion, @PathVariable Long id){
        try {
            var promotionActual = promotionServices.findById(id);
            promotionActual.setDescription(promotion.getDescription());
            promotionActual.setStartDate(promotion.getStartDate());
            promotionActual.setEndDate(promotion.getEndDate());
            promotionActual.setVideogame(promotion.getVideogame());
            var promotionSave = promotionServices.save(promotionActual);
            return new ResponseEntity<>(promotionSave, HttpStatus.CREATED);
        }catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("{id}")
    ResponseEntity<?> delete(@PathVariable Long id){
        try {
            var promotionDelete = promotionServices.deleteById(id);
            return new ResponseEntity<>(promotionDelete, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

}
