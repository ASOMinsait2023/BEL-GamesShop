package com.minsait.utils;

import com.minsait.models.Shop;
import com.minsait.models.Stock;
import com.minsait.models.dto.StockDTO;
import com.minsait.models.dto.StockDTOClient;
import com.minsait.models.dto.VideoGameDTO;

import java.math.BigDecimal;

public class Data {
    public static Shop newShop1() {
        return new Shop(1L, "Shop 1", "Address 1", "10:00", "6:00", "1234567890");
    }
    public static Shop newShop2() {
        return new Shop(2L, "Shop 2", "Address 2", "12:00", "7:00", "9876543210");
    }
    public static Shop newShopError() {
        return new Shop(1L, "Shop 2", "Address 2", "09:00asd", "7:00asdwq", "9876543210");
    }
    public static Stock newStock1() {
        return new Stock(1L, newShop1(), 1L, new VideoGameDTO(1L, "Game 1", "Description 1", "2022-01-01", BigDecimal.valueOf(50.00)), 10);
    }
    public static Stock newStock() {
        return new Stock(1L, newShop1(), 1L, new VideoGameDTO(1L, "Game 1", "Description 1", "2022-01-01", BigDecimal.valueOf(50.00)), 10);
    }
    public static Stock newStock2() {
        return new Stock(2L, newShop2(), 2L, new VideoGameDTO(2L, "Game 2", "Description 2", "2022-02-01", BigDecimal.valueOf(60.00)), 20);
    }
    public static StockDTO newStockDTO1() {
        return new StockDTO(1L, 1L, 10);
    }
    public static StockDTO newStockDTO2() {
        return new StockDTO(2L, 2L, 20);
    }
    public static VideoGameDTO newVideoGameDTO1() {
        return VideoGameDTO.builder()
                .id(1L)
                .name("Game 1")
                .description("Description 1")
                .releaseDate("2022-01-01")
                .price(BigDecimal.valueOf(50.00))
                .build();
    }

    public static VideoGameDTO newVideoGameDTO2() {
        return VideoGameDTO.builder()
                .id(2L)
                .name("Game 2")
                .description("Description 2")
                .releaseDate("2022-02-01")
                .price(BigDecimal.valueOf(60.00))
                .build();
    }
    public static StockDTOClient newStockDTOClient1() {;
        return new StockDTOClient(1L, newShop1(), newVideoGameDTO1(), 10);
    }

    public static StockDTOClient newStockDTOClient2() {
        return new StockDTOClient(2L, newShop1(), newVideoGameDTO2(), 20);
    }
}
