package com.minsait.controllers;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minsait.models.Stock;
import com.minsait.services.IStockService;
import com.minsait.utils.Constants;
import com.minsait.utils.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(StockController.class)
public class StockControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private IStockService stockService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testSave() throws Exception {
        Stock stock = Data.newStock();

        mvc.perform(post(Constants.URL_STOCK.getValue() +"/create")
                        .contentType(Constants.CONTENT_TYPE.getValue())
                        .content(mapper.writeValueAsString(stock)))
                .andExpect(status().isCreated());
    }

    @Test
    void testFindAll() throws Exception {
        when(stockService.findAll()).thenReturn(List.of(Data.newStock()));

        mvc.perform(get(Constants.URL_STOCK.getValue() ))
                .andExpect(status().isOk());
    }

    @Test
    void testFindById() throws Exception {
        long id = 1L;
        Stock stock = Data.newStock();
        stock.setId(id);

        when(stockService.findById(id)).thenReturn(stock);

        mvc.perform(get(Constants.URL_STOCK.getValue() +"/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void testDelete() throws Exception {
        long id = 1L;

        mvc.perform(delete(Constants.URL_STOCK.getValue() +"/{id}", id))
                .andExpect(status().isOk());

        verify(stockService, times(1)).deleteById(id);
    }

    @Test
    void testUpdate() throws Exception {
        long id = 1L;
        Stock existingStock = Data.newStock();
        Stock newStock = Data.newStock();
        newStock.setId(id);

        when(stockService.findById(id)).thenReturn(existingStock);
        when(stockService.save(existingStock)).thenReturn(newStock);

        mvc.perform(put(Constants.URL_STOCK.getValue() +"/update/{id}", id)
                        .contentType(Constants.CONTENT_TYPE.getValue())
                        .content(mapper.writeValueAsString(newStock)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.videogame").value(newStock.getVideogame()));
    }

    @Test
    void testFindByIdNoSuchElementException() throws Exception {
        long id = 1L;
        when(stockService.findById(id)).thenThrow(NoSuchElementException.class);

        mvc.perform(get(Constants.URL_STOCK.getValue() +"/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteNoSuchElementException() throws Exception {
        long id = 1L;
        when(stockService.deleteById(id)).thenThrow(NoSuchElementException.class);

        mvc.perform(delete(Constants.URL_STOCK.getValue() +"/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateNoSuchElementException() throws Exception {
        long id = 1L;
        Stock newStock = Data.newStock();
        newStock.setId(id);

        when(stockService.findById(id)).thenReturn(null);

        mvc.perform(put(Constants.URL_STOCK.getValue() +"/update/{id}", id)
                        .contentType(Constants.CONTENT_TYPE.getValue())
                        .content(mapper.writeValueAsString(newStock)))
                .andExpect(status().isNotFound());
    }
}
