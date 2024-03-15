package com.minsait.models.dto;

import com.minsait.controllers.Datos;
import com.minsait.models.Promotion;
import com.minsait.models.VideoGame;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PromotionDTOTest {


    @Test
     void testCreatePromotionDTO() {

    VideoGame videoGame = Datos.createVideogame1().get();
    Promotion promotion = Datos.createPromotion().get();

        var promotionDTO = new PromotionDTO();
        promotionDTO.setId(promotion.getId());
        promotionDTO.setDescription(promotion.getDescription());
        promotionDTO.setStartDate(promotion.getStartDate());
        promotionDTO.setEndDate(promotion.getEndDate());
        promotionDTO.setPercentage(promotion.getPercentage());
        promotionDTO.setIdVideoGame(videoGame.getId());
        promotionDTO.setName(videoGame.getName());
        promotionDTO.setPrice(videoGame.getPrice());

        assertEquals(promotion.getId(), promotionDTO.getId());
        assertEquals(promotion.getDescription(), promotionDTO.getDescription());
        assertEquals(promotion.getStartDate(), promotionDTO.getStartDate());
        assertEquals(promotion.getEndDate(), promotionDTO.getEndDate());
        assertEquals(promotion.getPercentage(), promotionDTO.getPercentage());
        assertEquals(videoGame.getId(), promotionDTO.getIdVideoGame());
        assertEquals(videoGame.getName(), promotionDTO.getName());
        assertEquals(videoGame.getPrice(), promotionDTO.getPrice());


    }

}