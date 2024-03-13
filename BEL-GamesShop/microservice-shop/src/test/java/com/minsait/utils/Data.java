package com.minsait.utils;

import com.minsait.models.Shop;
import com.minsait.models.Stock;

import java.util.Optional;

public class Data {
    public static Shop newShop1(){
        return new Shop(5L, "Antara Polanco", "Av. Ejército Nacional Mexicano 843-B, Granada, Miguel Hidalgo, 11520 Ciudad de México, CDMX", "11:00", "20:00", "55 4593 8870", null);
    }
    public static Shop newShop2(){
        return new Shop(6L, "Antara Polanco", "Av. Ejército Nacional Mexicano 843-B, Granada, Miguel Hidalgo, 11520 Ciudad de México, CDMX", "11:00", "20:00", "55 4593 8870", null);
    }
    public static Stock newStock1(Shop shop) {
        return new Stock(1L, shop, 1L, 10);
    }
    public static Stock newStock() {
        return new Stock(1L, newShop1(), 1L, 10);
    }
    public static Stock  newStock2(Shop shop) {
        return new Stock(2L, shop, 2L, 20);
    }
}
