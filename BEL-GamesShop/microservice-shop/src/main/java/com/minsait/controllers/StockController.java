package com.minsait.controllers;

import com.minsait.models.Shop;
import com.minsait.models.Stock;
import com.minsait.models.dto.StockDTO;
import com.minsait.models.dto.StockDTOClient;
import com.minsait.models.dto.VideoGameDTO;
import com.minsait.services.IShopService;
import com.minsait.services.IStockService;
import com.minsait.services.clients.IVideoGameClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"api/v1/stock"})
public class StockController {
    @Autowired
    IStockService stockService;
    @Autowired
    IVideoGameClient videoGameClient;
    @Autowired
    IShopService shopService;
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    void saveStock(@RequestBody Stock stock){
        stockService.save(stock);
    }

    @GetMapping
    public ResponseEntity<?> findAllStock(){
        List<StockDTOClient> stocks = stockService.findAll()
            .stream()
            .map(this::mapStockToStockDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(stocks);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            StockDTOClient stockDTO = mapStockToStockDTO(stockService.findById(id));
            return ResponseEntity.ok(stockDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable Long id) {
            stockService.deleteById(id);
            return ResponseEntity.ok(true);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody StockDTO stockDTO) {
        try {
            Stock existingStock = stockService.findById(id);
            Shop shop = shopService.findById(stockDTO.getShop());
            if (existingStock == null || shop == null) {
                throw new NoSuchElementException();
            }
            existingStock.setShop(shop);
            existingStock.setVideogame(stockDTO.getVideogame());
            existingStock.setStock(stockDTO.getStock());
            stockService.save(existingStock);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    private StockDTOClient mapStockToStockDTO(Stock stock) {
        VideoGameDTO videoGameDTO = videoGameClient.findById(stock.getVideogame());
        return StockDTOClient.builder()
                .id(stock.getId())
                .shop(stock.getShop())
                .videoGameDTO(videoGameDTO)
                .stock(stock.getStock())
                .build();
    }
}
