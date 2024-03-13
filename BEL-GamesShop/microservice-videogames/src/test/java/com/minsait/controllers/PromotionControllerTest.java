package com.minsait.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.minsait.models.Promotion;
import com.minsait.models.VideoGame;
import com.minsait.services.IPromotionServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(PromotionController.class)
class PromotionControllerTest {

    @MockBean
    IPromotionServices promotionServices;
    @Autowired
    private MockMvc mvc;

    private ObjectMapper mapper;


    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }


    @Test
    void testFindAllPromotion() throws Exception{
        when(promotionServices.findAll()).thenReturn(List.of(
                Datos.createPromotion().get(),
                Datos.createPromotion2().get()
        ));

        mvc.perform(get("/api/v1/promotion"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].description").value("Promotion1"));

        verify(promotionServices, times(1)).findAll();

    }

    @Test
    void testFindByIdPromotion() throws Exception{

        when(promotionServices.findById(1L)).thenReturn(Datos.createPromotion().get());
        mvc.perform(get("/api/v1/promotion/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.description").value("Promotion1"))
                .andExpect(jsonPath("$.startDate").value("2024-06-29"))
                .andExpect(jsonPath("$.endDate").value("2024-07-13"));

        verify(promotionServices, times(1)).findById(1L);

    }
    @Test
    void testFindByIdNotFound() throws Exception{
        when(promotionServices.findById(1L)).thenThrow(new NoSuchElementException());
        mvc.perform(get("/api/v1/promotion/1"))
                .andExpect(status().isNotFound());
    }


    @Test
    void testSave() throws Exception{

        var promotion = new Promotion(null, "La primavera está a la vuelta de la esquina y celebrar el cambio de estación de la mejor forma posible, ya que la tienda de Valve ha iniciado la promoción Rebajas de Primavera de 2024, en la que el precio de cientos de videojuegos se ha reducido considerablemente durante unos cuantos días.",
                LocalDate.parse("2024-03-06"),
                LocalDate.parse("2025-04-01"),
                50, new VideoGame());

        var response = new HashMap<String, Object>();
        response.put("fecha", LocalDate.now().toString());
        response.put("peticion", promotion);
        response.put("status", "CREATED");
        response.put("mensaje", "A new Promotion has been created");
        doReturn(promotion).when(promotionServices).save(promotion);

        mvc.perform(post("/api/v1/promotion/create")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(promotion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.peticion.startDate").value("2024-03-06"))
                .andExpect(jsonPath("$.peticion.endDate").value("2025-04-01"));
    }
    @Test
    void testSaveNoNoSuchElementException() throws Exception{

        var promotion = new Promotion(null, "La primavera está a la vuelta de la esquina y celebrar el cambio de estación de la mejor forma posible, ya que la tienda de Valve ha iniciado la promoción Rebajas de Primavera de 2024, en la que el precio de cientos de videojuegos se ha reducido considerablemente durante unos cuantos días.",
                LocalDate.parse("2024-03-06"),
                LocalDate.parse("2025-04-01"),
                50,new VideoGame());

        doThrow(new NoSuchElementException()).when(promotionServices).save(promotion);

        mvc.perform(post("/api/v1/promotion/create")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(promotion)))
                .andExpect(status().isNotFound());
    }
    @Test
    void testSaveException() throws Exception{

        var promotion = new Promotion(null, "La primavera está a la vuelta de la esquina y celebrar el cambio de estación de la mejor forma posible, ya que la tienda de Valve ha iniciado la promoción Rebajas de Primavera de 2024, en la que el precio de cientos de videojuegos se ha reducido considerablemente durante unos cuantos días.",
                LocalDate.parse("2024-03-06"),
                LocalDate.parse("2025-04-01"),
                50, new VideoGame());

        doThrow(new RuntimeException("Promotion could not be created")).when(promotionServices).save(promotion);

        mvc.perform(post("/api/v1/promotion/create")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(promotion)))
                .andExpect(status().isBadRequest());
    }



    @Test
    void testUpdate() throws Exception {

        Promotion promotion = Datos.createPromotion().get();
        when(promotionServices.findById(1L)).thenReturn(promotion);
        Promotion promotionUpdate = new Promotion();
        promotionUpdate.setDescription("La primavera está a la vuelta de la esquina y celebrar el cambio de estación de la mejor forma posible, ya que la tienda de Valve ha iniciado la promoción Rebajas de Primavera de 2024, en la que el precio de cientos de videojuegos se ha reducido considerablemente durante unos cuantos días.");
        promotionUpdate.setStartDate(LocalDate.parse("2024-03-06"));
        promotionUpdate.setEndDate(LocalDate.parse("2025-04-01"));
        promotionUpdate.setPercentage(50);
        promotionUpdate.setVideogame(new VideoGame(1L, "PAYDAY 2 ", " is an action-packed, four-player co-op " +
                "shooter that once again lets gamers don the masks of the original PAYDAY crew - Dallas, Hoxton, " +
                "Wolf and Chains - as they descend on Washington DC for an epic crime spree",
                "2013-08-13", new BigDecimal("280.50"),List.of( new Promotion())));
        when(promotionServices.save((any(Promotion.class)))).thenReturn(promotionUpdate);

        mvc.perform(put("/api/v1/promotion/1")
                .contentType("application/json")
                .content(mapper.writeValueAsString(promotionUpdate)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description").value("La primavera está a la vuelta de la esquina y celebrar el cambio de estación de la mejor forma posible, ya que la tienda de Valve ha iniciado la promoción Rebajas de Primavera de 2024, en la que el precio de cientos de videojuegos se ha reducido considerablemente durante unos cuantos días."));


    }

    @Test
    void testUpdateNotFoundException() throws Exception {
        when(promotionServices.findById(1L)).thenThrow(new NoSuchElementException("promotion not found"));

        mvc.perform(put("/api/v1/promotion/1")
                .contentType("application/json")
                .content(mapper.writeValueAsString(Datos.createPromotion().get())))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateBadRequestException() throws Exception {
        when(promotionServices.findById(1L)).thenThrow(new IllegalArgumentException("Invalid game data"));

        mvc.perform(put("/api/v1/promotion/1")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(Datos.createPromotion().get())))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDelete() throws Exception{
        when(promotionServices.deleteById(1L)).thenReturn(true);
        mvc.perform(delete("/api/v1/promotion/1")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
    @Test
    void testDeleteNotFoundException() throws Exception {
        doThrow(new NoSuchElementException()).when(promotionServices).deleteById(1L);
        mvc.perform(delete("/api/v1/promotion/1")
                        .contentType("application/json"))
                .andExpect(status().isNotFound());
    }
}