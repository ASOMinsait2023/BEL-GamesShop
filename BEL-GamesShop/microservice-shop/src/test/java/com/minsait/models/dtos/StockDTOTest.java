package com.minsait.models.dtos;

import com.minsait.models.dto.StockDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class StockDTOTest {
    @Test
    public void testConstructorWithAllArguments() {
        Long id = 1L;
        Long videogame = 2L;
        Integer stockQuantity = 10;
        StockDTO stockDTO = new StockDTO(id, videogame, stockQuantity);
        assertEquals(id, stockDTO.getId());
        assertEquals(videogame, stockDTO.getVideogame());
        assertEquals(stockQuantity, stockDTO.getStock());
    }
    @Test
    public void testToString() {
        Long id = 1L;
        Long videogame = 2L;
        Integer stockQuantity = 10;
        StockDTO stockDTO = new StockDTO(id, videogame, stockQuantity);
        String toStringResult = stockDTO.toString();

        String expectedToString = "StockDTO(id=1, videogame=2, stock=10)";
        assertEquals(expectedToString, toStringResult);
    }
}
