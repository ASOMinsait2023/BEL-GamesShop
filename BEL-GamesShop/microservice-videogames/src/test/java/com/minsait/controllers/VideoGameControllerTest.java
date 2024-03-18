package com.minsait.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minsait.exception.PromotionException;
import com.minsait.models.Promotion;
import com.minsait.models.VideoGame;
import com.minsait.services.IPromotionServices;
import com.minsait.services.IVideoGameServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(VideoGameController.class)
class VideoGameControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IVideoGameServices videoGameServices;

    @MockBean
    private IPromotionServices promotionServices;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    void testFindAllVideoGame() throws Exception {
        when(videoGameServices.findAll()).thenReturn(List.of(
                Datos.createVideogame1().get(),
                Datos.createVideogame2().get(),
                Datos.createVideogame3().get()));


        mockMvc.perform(get("/api/v1/videogames"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name").value("Left 4 Dead 2"))
                .andExpect(jsonPath("$[1].name").value("Red Dead Redemption 2"))
                .andExpect(jsonPath("$[2].name").value("Dead by Daylight"));

        verify(videoGameServices, times(1)).findAll();
    }

    @Test
    void testFindById() throws Exception{

        when(videoGameServices.findById(1L)).thenReturn(Datos.createVideogame1().get());
        mockMvc.perform(get("/api/v1/videogames/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Left 4 Dead 2"));

    }
    @Test
    void testFindByIdNotFound() throws Exception{
        when(videoGameServices.findById(1L)).thenThrow(new NoSuchElementException());
        mockMvc.perform(get("/api/v1/videogames/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSave() throws Exception{
        var promotion = new Promotion();
        var videogame = new VideoGame(null, "PAYDAY 2 ", " is an action-packed, four-player co-op " +
                "shooter that once again lets gamers don the masks of the original PAYDAY crew - Dallas, Hoxton, " +
                "Wolf and Chains - as they descend on Washington DC for an epic crime spree",
                "2013-08-13", new BigDecimal("280.50"), List.of(promotion));

        var response = new HashMap<String, Object>();
        response.put("fecha", LocalDate.now().toString());
        response.put("peticion", videogame);
        response.put("status", "CREATED");
        response.put("mensaje", "Se ha creado un nuevo juego");
        doReturn(videogame).when(videoGameServices).save(videogame);


        mockMvc.perform(post("/api/v1/videogames/create")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(videogame)))
                .andExpectAll(
                        status().isOk(),
                        content().json(mapper.writeValueAsString(response))
                );
    }
    @Test
    void testSaveWithException() throws Exception{
        var videogame = new VideoGame(null, "PAYDAY 2 ", " is an action-packed, four-player co-op " +
                "shooter that once again lets gamers don the masks of the original PAYDAY crew - Dallas, Hoxton, " +
                "Wolf and Chains - as they descend on Washington DC for an epic crime spree",
                "2013-08-13", new BigDecimal("280.50"), List.of( new Promotion()));
        doThrow(new RuntimeException("Error al guardar el videojuego")).when(videoGameServices).save(any());
        mockMvc.perform(post("/api/v1/videogames/create")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(videogame)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void testUpdate() throws Exception {

        VideoGame videoGame = Datos.createVideogame1().get();

        when(videoGameServices.findById(1L)).thenReturn(videoGame);
        VideoGame videoGameUpdate = new VideoGame();
        videoGameUpdate.setName("Gears of War 3");
        videoGameUpdate.setDescription("Es un juego de disparos en tercera persona que enfatiza el uso de tácticas");
        videoGameUpdate.setReleaseDate("2011-09-20");
        videoGameUpdate.setPrice(new BigDecimal("229.00"));
        when(videoGameServices.save((any(VideoGame.class)))).thenReturn(videoGameUpdate);

        mockMvc.perform(put("/api/v1/videogames/1")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(videoGameUpdate)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Gears of War 3"));
    }

    @Test
    void testUpdateNotFoundException() throws Exception {
        when(videoGameServices.findById(1L)).thenThrow(new NoSuchElementException("Video game not found"));

        mockMvc.perform(put("/api/v1/videogames/1")
                .contentType("application/json")
                        .content(mapper.writeValueAsString(Datos.createVideogame1().orElseThrow(null))))
                .andExpect(status().isNotFound());

    }
    @Test
    void testUpdateBadRequestException() throws Exception {
        when(videoGameServices.save(any(VideoGame.class))).thenThrow(new IllegalArgumentException("Invalid game data"));

        mockMvc.perform(put("/api/v1/videogames/1")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(Datos.createVideogame1().orElseThrow(null))))
                .andExpect(status().isBadRequest());

    }

    @Test
    void testDelete() throws Exception {
        when(videoGameServices.deleteById(1L)).thenReturn(true);
        mockMvc.perform(delete("/api/v1/videogames/1")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteNotFoundException() throws Exception {
        doThrow(new NoSuchElementException()).when(videoGameServices).deleteById(1L);
        mockMvc.perform(delete("/api/v1/videogames/1")
                        .contentType("application/json"))
                .andExpect(status().isNotFound());

    }

    @Test
    void getVideoGameWithDiscount() throws Exception{
        when(videoGameServices.getVideoGameWithDiscount(1L)).thenReturn(Datos.createVideogame1().stream().toList());

        mockMvc.perform(get("/api/v1/videogames/1"))
                .andExpect(status().isOk());


    }
    @Test
    void testGetVideoGameWithDiscount_NoPromotionsAvailable() throws Exception {
        when(videoGameServices.getVideoGameWithDiscount(1L)).thenThrow(new PromotionException("There are no promotions available for the current date."));

        mockMvc.perform(get("/api/v1/videogames/discount/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("There are no promotions available for the current date."));
    }

    @Test
    void testGetVideoGameWithDiscount_VideoGameNotFound() throws Exception {
        when(videoGameServices.getVideoGameWithDiscount(1L)).thenThrow(new NoSuchElementException());

        mockMvc.perform(get("/api/v1/videogames/discount/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetVideoGameSearchByIdPromotion() throws Exception{

        Promotion promotion = Datos.createPromotion().get();
        Promotion promotion1 = Datos.createPromotion2().get();
        List<Promotion> promotions = Arrays.asList(promotion, promotion1);
        when(promotionServices.getPromotionSearchVideogameById(1L)).thenReturn(promotions);

        mockMvc.perform(get("/api/v1/videogames/promotion/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }
    @Test
    void testGetVideoGameSearchByIdPromotionNotFound() throws Exception{

        when(promotionServices.getPromotionSearchVideogameById(1L)).thenThrow(new NoSuchElementException());

        mockMvc.perform(get("/api/v1/videogames/promotion/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetPromotionCountForVideoGame() throws Exception{

        Long videoGameId = 1L;
        VideoGame videoGame = Datos.createVideogame1().get();

        List<Promotion> promotions = List.of(Datos.createPromotion().get(), Datos.createPromotion2().get());
        videoGame.setPromotions(promotions);

        when(videoGameServices.findById(videoGameId)).thenReturn(videoGame);

        mockMvc.perform(get("/api/v1/videogames/promotion-count/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.videoGameId").value(1))
                .andExpect(jsonPath("$.videoGameName").value("Left 4 Dead 2"))
                .andExpect(jsonPath("$.promotionCount").value(2));

        verify(videoGameServices, times(1)).findById(videoGameId);

    }

    @Test
    void testGetPromotionCountForVideoGameNotExist() throws Exception{

        Long videoGameId = 1L;

        when(videoGameServices.findById(videoGameId)).thenReturn(null);

        mockMvc.perform(get("/api/v1/videogames/promotion-count/1"))
                .andExpect(status().isNotFound());

        verify(videoGameServices, times(1)).findById(videoGameId);

    }
    @Test
    void testGetPromotionCountForVideoGameNoSuchElementException() throws Exception{

        Long videoGameId = 1L;;

        when(videoGameServices.findById(videoGameId)).thenThrow(new NoSuchElementException());

        mockMvc.perform(get("/api/v1/videogames/promotion-count/1"))
                .andExpect(status().isNotFound());

        verify(videoGameServices, times(1)).findById(videoGameId);


    }
}