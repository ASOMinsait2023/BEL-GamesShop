package com.minsait.controllers;



import com.minsait.exception.PromotionException;
import com.minsait.models.Promotion;
import com.minsait.models.VideoGame;
import com.minsait.models.dto.PromotionDTO;
import com.minsait.services.IPromotionServices;
import com.minsait.services.IVideoGameServices;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/videogames")
@Slf4j
public class VideoGameController {


    @Autowired
    IVideoGameServices videoGameServices;

    @Autowired
    IPromotionServices promotionServices;


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
    @GetMapping("/discount/{videoGameId}")
    ResponseEntity<?> getVideoGameWithDiscount(@PathVariable Long videoGameId) {

        try {
            return  ResponseEntity.ok(videoGameServices.getVideoGameWithDiscount(videoGameId));
        } catch (PromotionException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("There are no promotions available for the current date.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/promotion/{videoGameId}")
    ResponseEntity<?> getVideoGameSearchByIdPromotion(@PathVariable Long videoGameId){
        try {
            List<Promotion> promotions = promotionServices.getPromotionSearchVideogameById(videoGameId);
            List<PromotionDTO> promotionDTOList = promotions.stream()
                    .map(promotion -> new PromotionDTO(promotion.getId(), promotion.getDescription(), promotion.getStartDate(),
                            promotion.getEndDate(), promotion.getPercentage(), promotion.getVideogame().getId(), promotion.getVideogame().getName(),
                            promotion.getVideogame().getPrice()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(promotionDTOList);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/promotion-count/{id}")
    public ResponseEntity<?> getPromotionCountForVideoGame(@PathVariable Long id) {
        try {
            VideoGame videoGame = videoGameServices.findById(id);
            if (videoGame != null) {
                int promotionCount = videoGame.calculatePromotionCount();
                Map<String, Object> response = new HashMap<>();
                response.put("videoGameId", videoGame.getId());
                response.put("videoGameName", videoGame.getName());
                response.put("promotionCount", promotionCount);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search-category/{Categoryid}")
    public ResponseEntity<?> findCategoryByVideoGameId(@PathVariable Long Categoryid){
        try {
            return ResponseEntity.ok(videoGameServices.findCategoryByVideoGameId(Categoryid));
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

}
