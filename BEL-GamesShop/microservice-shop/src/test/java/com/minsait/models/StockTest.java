package com.minsait.models;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class StockTest {
    private Stock stock;

    @BeforeEach
    public void setUp() {
        stock = new Stock();
    }
    @Test
    public void testGettersAndSetters() {
        Long id = 1L;
        Shop shop = new Shop();
        Long videogame = 2L;
        Integer stockQuantity = 10;
        stock.setId(id);
        stock.setShop(shop);
        stock.setVideogame(videogame);
        stock.setStock(stockQuantity);
        assertEquals(id, stock.getId());
        assertEquals(shop, stock.getShop());
        assertEquals(videogame, stock.getVideogame());
        assertEquals(stockQuantity, stock.getStock());
    }
    @Test
    public void testNoArgsConstructor() {
        Stock stock = new Stock();
        assertNotNull(stock);
    }
   /* @Test
    public void testConstructorWithAllArguments() {
        Long id = 1L;
        Shop shop = new Shop();
        Long videogame = 2L;
        Integer stockQuantity = 10;
        Stock stock = new Stock(id, shop, videogame, stockQuantity);
        assertNotNull(stock);
        assertEquals(id, stock.getId());
        assertEquals(shop, stock.getShop());
        assertEquals(videogame, stock.getVideogame());
        assertEquals(stockQuantity, stock.getStock());
    }*/
}
