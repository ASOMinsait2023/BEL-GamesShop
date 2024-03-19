package com.minsait.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minsait.models.Shop;
import com.minsait.models.dto.StockDTOClient;
import com.minsait.services.IShopService;
import com.minsait.services.IStockService;
import com.minsait.utils.Constants;
import com.minsait.utils.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
@WebMvcTest(ShopController.class)
public class ShopControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IShopService shopService;
    @MockBean
    private IStockService stockService;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testSave() throws Exception {
        when(shopService.save(any(Shop.class))).thenReturn(Data.newShop1());
        mockMvc.perform(post(Constants.URL_SHOP.getValue()+"/create")
                        .contentType(Constants.CONTENT_TYPE.getValue())
                        .content(mapper.writeValueAsString(Data.newShop1()))
                        .accept(Constants.CONTENT_TYPE.getValue()))
                .andExpect(status().isCreated());
        verify(shopService, times(1)).save(any(Shop.class));
    }

    @Test
    public void testFindAll() throws Exception {
        List<Shop> shopsList = new ArrayList<>();
        shopsList.add(Data.newShop1());
        shopsList.add(Data.newShop2());
        when(shopService.findAll()).thenReturn(shopsList);
        mockMvc.perform(get(Constants.URL_SHOP.getValue())
                        .accept(Constants.CONTENT_TYPE.getValue()))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.size()").value(2)
                );
        verify(shopService, times(1)).findAll();
    }
    @Test
    public void testSaveException() throws Exception {
        mockMvc.perform(post(Constants.URL_SHOP.getValue()+"/create")
                        .contentType(Constants.CONTENT_TYPE.getValue())
                        .content(mapper.writeValueAsString(Data.newShopError()))
                        .accept(Constants.CONTENT_TYPE.getValue()))
                .andExpect(content().string("Invalid time format. Please use 24-hour format (HH:mm)."));
        verify(shopService, never()).save(any(Shop.class));
    }

    @Test
    public void testFindById() throws Exception {
        when(shopService.findById(1L)).thenReturn(Data.newShop1());
        mockMvc.perform(get(Constants.URL_SHOP.getValue()+"/1")
                        .accept(Constants.CONTENT_TYPE.getValue()))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.name").value("Shop 1")

                );
        verify(shopService, times(1)).findById(1L);
    }

    @Test
    public void testDelete() throws Exception {
        when(shopService.delleteById(1L)).thenReturn(true);
        mockMvc.perform(delete(Constants.URL_SHOP.getValue()+"/1")
                        .accept(Constants.CONTENT_TYPE.getValue()))
                .andExpect(status().isOk());
        verify(shopService, times(1)).delleteById(1L);
    }

    @Test
    public void testSearchUbication() throws Exception {
        when(shopService.queryForAddress(Data.newShop1().getAddress())).thenReturn(Collections.singletonList(Data.newShop1()));
        mockMvc.perform(get(Constants.URL_SHOP.getValue()+"/searchUbication/"+Data.newShop1().getAddress())
                        .accept(Constants.CONTENT_TYPE.getValue()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(Data.newShop1().getName()));
        verify(shopService, times(1)).queryForAddress(Data.newShop1().getAddress());
    }

    @Test
    public void testDeleteException() throws Exception {
        doThrow(NoSuchElementException.class).when(shopService).delleteById(anyLong());
        mockMvc.perform(delete(Constants.URL_SHOP.getValue()+"/2189231782319")
                        .accept(Constants.CONTENT_TYPE.getValue()))
                .andExpect(status().isNotFound());
    }
    @Test
    public void testFindByIdException() throws Exception {
        when(shopService.findById(anyLong())).thenThrow(NoSuchElementException.class);
        mockMvc.perform(get(Constants.URL_SHOP.getValue()+"/921233212132199")
                        .accept(Constants.CONTENT_TYPE.getValue()))
                .andExpect(status().isNotFound());
    }
    @Test
    public void testSearchUbicationException() throws Exception {
        when(shopService.queryForAddress(anyString())).thenThrow(NoSuchElementException.class);
        mockMvc.perform(get(Constants.URL_SHOP.getValue()+"/searchUbication/"+Data.newShopError().getAddress())
                        .accept(Constants.CONTENT_TYPE.getValue()))
                .andExpect(status().isNotFound());
    }
    @Test
    public void update_ExistingIdAndValidShop_ReturnsCreated() throws Exception {
        Shop shop = Data.newShop1();
        when(shopService.findById(1L)).thenReturn(shop);
        when(shopService.save(any(Shop.class))).thenReturn(shop);
        shop.setName("Updated");
        mockMvc.perform(put(Constants.URL_SHOP.getValue()+"/update/1")
                        .contentType(Constants.CONTENT_TYPE.getValue())
                        .content(mapper.writeValueAsString(shop))
                        .accept(Constants.CONTENT_TYPE.getValue()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Updated"));
        verify(shopService, times(1)).findById(1L);
        verify(shopService, times(1)).save(any(Shop.class));
    }

    @Test
    public void testUpdateException() throws Exception {
        when(shopService.findById(1L)).thenReturn(null);
        mockMvc.perform(put(Constants.URL_SHOP.getValue()+"/update/1")
                        .contentType(Constants.CONTENT_TYPE.getValue())
                        .content(mapper.writeValueAsString(Data.newShop1()))
                        .accept(Constants.CONTENT_TYPE.getValue()))
                .andExpect(status().isBadRequest());
        verify(shopService, times(1)).findById(1L);
        verify(shopService, times(0)).save(any(Shop.class));
    }
    @Test
    public void testGetStockByShopId() throws Exception {
        List<StockDTOClient> stockDTOClients = new ArrayList<>();
        stockDTOClients.add(Data.newStockDTOClient1());
        stockDTOClients.add(Data.newStockDTOClient2());
        when(stockService.getStockByShopId(anyLong())).thenReturn(stockDTOClients);
        mockMvc.perform(get(Constants.URL_SHOP.getValue()+"/stock/1")
                        .accept(Constants.CONTENT_TYPE.getValue()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
        verify(stockService, times(1)).getStockByShopId(1L);
    }
    @Test
    public void testGetStockByShopIdEx() throws Exception {
        when(stockService.getStockByShopId(anyLong())).thenThrow(NoSuchElementException.class);
        mockMvc.perform(get(Constants.URL_SHOP.getValue()+"/stock/999")
                        .accept(Constants.CONTENT_TYPE.getValue()))
                .andExpect(status().isNotFound());
        verify(stockService, times(1)).getStockByShopId(999L);
    }
}
