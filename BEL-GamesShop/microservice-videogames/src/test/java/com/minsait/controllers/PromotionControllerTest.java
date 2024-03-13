package com.minsait.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minsait.services.IPromotionServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

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
    }


    @Test
    void findAllPromotion() throws Exception{
        when(promotionServices.findAll()).thenReturn(List.of(
                Datos.createPromotion().get(),
                Datos.createPromotion2().get()
        ));

        mvc.perform(get("/api/v1/promotion"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].description").value("Las ofertas de verano son el momento más esperado por los jugadores de la tienda de Valve. Hasta el próximo 13 de julio a las 19:00 (CEST) tendrás acceso a descuentos en juegos de todo tipo del catálogo. Además de la importante rebaja de Steam Deck, lo que más les importa a los jugadores son los juegos. Repasamos los descuentos más destacados de este arranque del periodo festivo"));

        verify(promotionServices, times(1)).findAll();

    }

    @Test
    void findByIdPromotion() {

        when(promotionServices.findById(1L)).thenReturn(Datos.createPromotion().get());
        mvc.perform(get("/api/v1/promotion/1"))
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}