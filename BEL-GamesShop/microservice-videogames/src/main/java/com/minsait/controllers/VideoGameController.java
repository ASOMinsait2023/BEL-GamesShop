package com.minsait.controllers;


import com.minsait.models.Promotion;
import com.minsait.models.VideoGame;
import com.minsait.services.IPromotionServices;
import com.minsait.services.IVideoGameServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("api/v1/videogames")
@Slf4j
public class VideoGameController {

    @Autowired
    IPromotionServices promotionService;
    @Autowired
    IVideoGameServices videoGameServices;

    @GetMapping
    ResponseEntity<?> findAllVideoGame(){
    return ResponseEntity.ok(videoGameServices.findAll());
    }

    @GetMapping("{id}")
    ResponseEntity<?> findById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(videoGameServices.findById(id));
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
            }

    }

    @PostMapping("/create")
    ResponseEntity<?> save(@RequestBody VideoGame videoGame) {

        var response = new HashMap<String, Object>();
        response.put("fecha", LocalDate.now().toString());
        response.put("peticion", videoGame);

        try {
            videoGameServices.save(videoGame);
            response.put("status", "CREATED");
            response.put("mensaje", "Se ha creado un nuevo juego");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{id}")
    ResponseEntity<?>update(@RequestBody VideoGame videoGame, @PathVariable Long id){
    try {
        var videoGameActual = videoGameServices.findById(id);
        videoGameActual.setName(videoGame.getName());
        videoGameActual.setDescription(videoGame.getDescription());
        videoGameActual.setReleaseDate(videoGame.getReleaseDate());
        videoGameActual.setPrice(videoGame.getPrice());
        var videoGameSave = videoGameServices.save(videoGameActual);
        return new ResponseEntity<>(videoGameSave, HttpStatus.CREATED);
    }catch (NoSuchElementException e) {
        return ResponseEntity.notFound().build();
    } catch (Exception e) {
        return ResponseEntity.badRequest().build();
    }
    }

    @DeleteMapping("{id}")
    ResponseEntity<?> delete(@PathVariable Long id){
        try {
            var videogameDelete = videoGameServices.deleteById(id);
            return new ResponseEntity<>(videogameDelete, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/apply-promotion/{videogameId}")
    public ResponseEntity<?> applyPromotionToVideoGame(@PathVariable("videogameId") Long videogameId) {
        try {
            VideoGame videoGame = videoGameServices.findVideoGameById(videogameId);
            if (videoGame == null) {
                return ResponseEntity.notFound().build();
            }

            BigDecimal finalPrice = videoGame.getPrice();
            Promotion appliedPromotion = null;

            LocalDate currentDate = LocalDate.now();

            List<Promotion> promotions = promotionService.findPromotionsByVideoGameId(videogameId);
            for (Promotion promotion : promotions) {
                LocalDate startDate = promotion.getStartDate();
                LocalDate endDate = promotion.getEndDate();
                if (currentDate.isAfter(startDate) && currentDate.isBefore(endDate)) {
                    BigDecimal originalPrice = videoGame.getPrice();
                    BigDecimal discountedPrice = promotion.calculateDiscountedPrice(originalPrice);
                    finalPrice = discountedPrice;
                    appliedPromotion = promotion;
                    break;
                }
            }

            videoGame.setPrice(finalPrice);

            Map<String, Object> response = new HashMap<>();
            response.put("videoGame", videoGame);

            if (appliedPromotion != null) {
                // Incluir solo los campos necesarios de la promoción aplicada
                Map<String, Object> appliedPromotionData = new HashMap<>();
                appliedPromotionData.put("id", appliedPromotion.getId());
                appliedPromotionData.put("description", appliedPromotion.getDescription());
                appliedPromotionData.put("startDate", appliedPromotion.getStartDate());
                appliedPromotionData.put("endDate", appliedPromotion.getEndDate());
                appliedPromotionData.put("percentage", appliedPromotion.getPercentage());
                response.put("appliedPromotion", appliedPromotionData);
            } else {
                response.put("appliedPromotion", null);
            }

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
