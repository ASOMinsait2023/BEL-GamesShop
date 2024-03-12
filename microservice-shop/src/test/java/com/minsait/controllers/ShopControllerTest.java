package com.minsait.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minsait.models.Shop;
import com.minsait.models.Stock;
import com.minsait.services.IShopService;
import com.minsait.services.IStockService;
import com.minsait.utils.Constants;
import com.minsait.utils.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ShopController.class)
public class ShopControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private IShopService shopService;
    @MockBean
    private IStockService stockService;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp(){
        mapper = new ObjectMapper();
    }
    @Test
    void testSave() throws Exception{
        var shop = Data.newShop1();
        mvc.perform(MockMvcRequestBuilders.post(Constants.URL_SHOP.getValue()+"/create")
                        .contentType(Constants.CONTENT_TYPE.getValue())
                        .content(mapper.writeValueAsString(shop)))
                .andExpect(status().isCreated());
    }
    @Test
    void testFindAll() throws Exception{
        when(shopService.findAll()).thenReturn(List.of(Data.newShop1(), Data.newShop2()));
        mvc.perform(get(Constants.URL_SHOP.getValue())).andExpectAll(
                status().isOk(),
                jsonPath("$[0].name").value("Antara Polanco")
        );
    }
    @Test
    void testFindById() throws Exception{
        Long id = 1L;
        Shop shop = new Shop();
        shop.setId(id);

        when(shopService.findById(id)).thenReturn(shop);
        mvc.perform(get(Constants.URL_SHOP.getValue()+"/{id}", id))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.id").value(id)
                );
    }
    @Test
    void testDelete() throws Exception{
        Long id = 1L;
        mvc.perform(delete(Constants.URL_SHOP.getValue()+"/{id}", id))
                .andExpect(status().isOk());
        verify(shopService, times(1)).delleteById(id);
    }
    @Test
    void testSearchUbication() throws Exception {
        String address = "Av. Ejército Nacional Mexicano 843-B, Granada, Miguel Hidalgo, 11520 Ciudad de México, CDMX";
        List<Shop> list = new ArrayList<>();
        list.add(Data.newShop1());
        when(shopService.queryForAddress(address)).thenReturn(list);
        mvc.perform(get(Constants.URL_SHOP.getValue()+"/searchUbication/{address}", address))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$[0].address").value(address)
                );
    }
    @Test
    void testSearchUbicationNotFound() throws Exception{
        when(shopService.queryForAddress(Data.newShop1().getAddress())).thenThrow(new NoSuchElementException());
        mvc.perform(get(Constants.URL_SHOP.getValue()+"/searchUbication/{id}",Data.newShop1().getAddress()))
                .andExpect(status().isNotFound());
    }
    @Test
    void testUpdate() throws Exception{
        Shop shop = Data.newShop1();
        when(shopService.findById(shop.getId())).thenReturn(shop);
        shop.setName("TestingName");
        when(shopService.save(shop)).thenReturn(shop);

        mvc.perform(put(Constants.URL_SHOP.getValue()+"/update/{id}", shop.getId())
                .contentType(Constants.CONTENT_TYPE.getValue())
                .content(mapper.writeValueAsString(shop))).andExpectAll(
                        status().isCreated(),
                jsonPath("$.id").value(shop.getId()),
                jsonPath("$.name").value(shop.getName())
        );
    }
    @Test
    void testUpdateException() throws Exception{
        Shop shop = Data.newShop1();

        when(shopService.findById(shop.getId())).thenReturn(null);
        mvc.perform(put(Constants.URL_SHOP.getValue() + "/update/{id}", shop.getId())
                        .contentType(Constants.CONTENT_TYPE.getValue())
                        .content(mapper.writeValueAsString(shop)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void testGetStockByShopId() throws Exception{
        var shopId = Data.newShop1().getId();
        Stock stock1 = Data.newStock1(Data.newShop1());
        Stock stock2 = Data.newStock2(Data.newShop1());

        when(stockService.getStockByShopId(shopId)).thenReturn(List.of(stock1, stock2));

        mvc.perform(MockMvcRequestBuilders.get(Constants.URL_SHOP.getValue() + "/stock/{shopId}", shopId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(stock1.getId()))
                .andExpect(jsonPath("$[0].videogame").value(stock1.getVideogame()))
                .andExpect(jsonPath("$[0].stock").value(stock1.getStock()));
    }
    @Test
    void testFindByIdException() throws Exception {
        long id = 1L;
        when(shopService.findById(id)).thenThrow(NoSuchElementException.class);

        mvc.perform(get(Constants.URL_SHOP.getValue() + "/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteException() throws Exception {
        long id = 1L;
        when(shopService.delleteById(id)).thenThrow(NoSuchElementException.class);

        mvc.perform(delete(Constants.URL_SHOP.getValue() + "/{id}", id))
                .andExpect(status().isNotFound());
    }
    @Test
    void testGetStockByShopId_NoSuchElementException() throws Exception {
        long shopId = 1L;
        when(stockService.getStockByShopId(shopId)).thenThrow(NoSuchElementException.class);
        mvc.perform(get(Constants.URL_SHOP.getValue()+"/stock/{shopId}", shopId))
                .andExpect(status().isNotFound());
    }
}