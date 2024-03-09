package com.minsait.controllers;

import com.minsait.models.Stock;
import com.minsait.services.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping({"api/v1/stock"})
public class StockController {
    @Autowired
    IStockService stockService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    void saveStock(@RequestBody Stock stock){
        stockService.save(stock);
    }

    @GetMapping
    public ResponseEntity<?> findAllStock(){
        return ResponseEntity.ok(stockService.findAll());
    }
    @GetMapping({"/{id}"})
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(stockService.findById(id));
    }
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.deleteById(id));
    }
}
