package com.minsait.services;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.minsait.models.Stock;
import com.minsait.repositories.IStockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class StockServiceTest {
    @Mock
    private IStockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testFindAll() {
        List<Stock> stocks = List.of(new Stock(), new Stock());
        when(stockRepository.findAll()).thenReturn(stocks);
        List<Stock> result = stockService.findAll();
        assertEquals(stocks, result);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Stock stock = new Stock();
        stock.setId(id);
        when(stockRepository.findById(id)).thenReturn(Optional.of(stock));
        Stock result = stockService.findById(id);
        assertEquals(stock, result);
    }

    @Test
    void testSave() {
        Stock stock = new Stock();
        when(stockRepository.save(stock)).thenReturn(stock);
        Stock result = stockService.save(stock);
        assertEquals(stock, result);
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        Stock stock = new Stock();
        stock.setId(id);
        Optional<Stock> optionalStock = Optional.of(stock);
        when(stockRepository.findById(id)).thenReturn(optionalStock);
        doNothing().when(stockRepository).deleteById(id);
        assertTrue(stockService.deleteById(id));
        verify(stockRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteByIdStockNotFound() {
        Long id = 1L;
        Optional<Stock> optionalStock = Optional.empty();
        when(stockRepository.findById(id)).thenReturn(optionalStock);
        assertFalse(stockService.deleteById(id));
        verify(stockRepository, never()).deleteById(id);
    }

    @Test
    void testGetStockByShopId() {
        Long shopId = 1L;
        List<Stock> stocks = List.of(new Stock(), new Stock());
        when(stockRepository.findByShopId(shopId)).thenReturn(stocks);
        List<Stock> result = stockService.getStockByShopId(shopId);
        assertEquals(stocks, result);
    }
}

