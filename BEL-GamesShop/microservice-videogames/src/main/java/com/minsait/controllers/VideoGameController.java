package com.minsait.controllers;


import com.minsait.models.VideoGame;
import com.minsait.services.IVideoGameServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/videogames")
@Slf4j
public class VideoGameController {

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

}
