package com.minsait.controllers;
import com.minsait.exceptions.InvalidTimeFormatException;
import com.minsait.models.Shop;
import com.minsait.models.Stock;
import com.minsait.models.dto.StockDTO;
import com.minsait.services.IShopService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.minsait.services.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"api/v1/shop"})
public class ShopController {
    @Autowired
    IShopService shopService;
    @Autowired
    IStockService stockService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    void saveShop(@RequestBody Shop shop) {
        validateHoursFormat(shop.getOpeningHour(), shop.getClosedHour());
        this.shopService.save(shop);
    }
    private void validateHoursFormat(String openingHour, String closedHour) {
        final String TIMEREGUEX = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        if (!openingHour.matches(TIMEREGUEX) || !closedHour.matches(TIMEREGUEX)) {
            throw new InvalidTimeFormatException("Invalid time format. Please use 24-hour format (HH:mm).");
        }
    }

    @ExceptionHandler(InvalidTimeFormatException.class)
    public ResponseEntity<String> handleInvalidTimeFormatException(InvalidTimeFormatException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @GetMapping
    public ResponseEntity<?> findAllShop() {
        return ResponseEntity.ok(this.shopService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(this.shopService.findById(id));
        }catch (NoSuchElementException e){
            return ResponseEntity
                    .notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
        return ResponseEntity.ok(this.shopService.delleteById(id));
        }catch (NoSuchElementException e){
            return ResponseEntity
                    .notFound().build();
        }
    }

    @GetMapping("/searchUbication/{address}")
    public ResponseEntity<?> searchUbication(@PathVariable String address) {
        try {
            return ResponseEntity.ok(this.shopService.queryForAddress(address));
        } catch (NoSuchElementException var3) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update/{id}")
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody Shop shop){
        try{
            var shopFind = shopService.findById(id);
            shopFind.setAddress(shop.getAddress());
            shopFind.setName(shop.getName());
            shopFind.setClosedHour(shop.getClosedHour());
            shopFind.setOpeningHour(shop.getOpeningHour());
            shopFind.setPhoneNumber(shop.getPhoneNumber());
            return new ResponseEntity<>(shopService.save(shopFind), HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/stock/{shopId}")
    public ResponseEntity<List<StockDTO>> getStockByShopId(@PathVariable Long shopId) {
        try{
            List<Stock> stocks = stockService.getStockByShopId(shopId);
            List<StockDTO> stockResponses = stocks.stream()
                    .map(stock -> new StockDTO(stock.getId(), stock.getVideogame(), stock.getStock()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(stockResponses);

        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();

        }
    }

}
