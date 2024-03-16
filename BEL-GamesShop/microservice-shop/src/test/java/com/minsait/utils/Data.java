package com.minsait.utils;

import com.minsait.models.Shop;
import com.minsait.models.Stock;

public class Data {
    public static Shop newShop1(){
        return new Shop(5L);
    }
    public static Shop newShop2(){
        return new Shop(6L);
    }
    public static Shop newShopError(){
        return new Shop(6L);
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
