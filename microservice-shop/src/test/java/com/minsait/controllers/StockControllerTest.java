package com.minsait.controllers;

import com.minsait.models.Shop;
import com.minsait.models.Stock;
import com.minsait.models.dto.StockDTO;
import com.minsait.models.dto.StockDTOClient;
import com.minsait.services.IShopService;
import com.minsait.services.IStockService;
import com.minsait.services.clients.IVideoGameClient;
import com.minsait.utils.Data;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class StockControllerTest{

    @Mock
    private IStockService stockService;

    @Mock
    private IShopService shopService;
    @Mock
    private IVideoGameClient videoGameClient;

    @InjectMocks
    private StockController stockController;

    public StockControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveStockTest() {
        Stock stock = new Stock();
        stockController.saveStock(stock);
        verify(stockService, times(1)).save(stock);
    }
    @Test
    void findAllStockTest() {
        Stock stock1 = new Stock();
        Stock stock2 = new Stock();
        List<Stock> stocks = Arrays.asList(Data.newStock1(), Data.newStock2());
        when(stockService.findAll()).thenReturn(stocks);

        ResponseEntity<?> responseEntity = stockController.findAllStock();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, ((List<StockDTOClient>) responseEntity.getBody()).size());
    }

    @Test
    void findByIdTest() {
        Long id = 1L;
        Stock stock = new Stock();
        when(stockService.findById(id)).thenReturn(stock);

        ResponseEntity<?> responseEntity = stockController.findById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    void testFindByIdException() {
        Long id = 1L;
        when(stockService.findById(id)).thenThrow(NoSuchElementException.class);

        ResponseEntity<?> responseEntity = stockController.findById(id);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertFalse(responseEntity.hasBody());
    }

    @Test
    void deleteTest() {
        // Configurar el comportamiento esperado del mock
        when(stockService.deleteById(anyLong())).thenReturn(true);

        // Llamar al método delete de StockController
        ResponseEntity<?> response = stockController.delete(1L);

        // Verificar que el método delete(id) de stockService se llama exactamente una vez
        verify(stockService, times(1)).deleteById(1L);

        // Verificar el estado de la respuesta
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
    }

    @Test
    void updateTest() {
        Long id = 1L;
        StockDTO stockDTO = Data.newStockDTO1();
        Stock existingStock = new Stock();
        Shop shop = new Shop();
        when(stockService.findById(id)).thenReturn(existingStock);
        when(shopService.findById(stockDTO.getShop())).thenReturn(shop);

        ResponseEntity<?> responseEntity = stockController.update(id, stockDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody());
        verify(stockService, times(1)).save(existingStock);
    }

    @Test
    void updateTest_NotFound() {
        Long id = 1L;
        StockDTO stockDTO = Data.newStockDTO1();
        when(stockService.findById(id)).thenReturn(null);

        ResponseEntity<?> responseEntity = stockController.update(id, stockDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void updateTest_Exception() {
        Long id = 1L;
        StockDTO stockDTO = Data.newStockDTO1();
        when(stockService.findById(id)).thenThrow(new NoSuchElementException());

        ResponseEntity<?> responseEntity = stockController.update(id, stockDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }


}
