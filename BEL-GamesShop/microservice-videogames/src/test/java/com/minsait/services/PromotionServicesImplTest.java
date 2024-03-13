package com.minsait.services;

import com.minsait.controllers.Datos;
import com.minsait.models.Promotion;
import com.minsait.models.VideoGame;
import com.minsait.repositories.IPromotionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PromotionServicesImplTest {

    @Mock
    IPromotionRepository promotionRepository;

    @InjectMocks
    PromotionServicesImpl promotionServices;

    @Test
    void testFindAll() throws Exception {

        List<Promotion> promotions = List.of(
                Datos.createPromotion().get(),
                Datos.createPromotion2().get()
        );

        when(promotionRepository.findAll()).thenReturn(promotions);

        List<Promotion> result = promotionServices.findAll();

        assertEquals(2, result.size());
        assertEquals("Promotion1", result.get(0).getDescription());
        assertEquals("Promotion2", result.get(1).getDescription());

        verify(promotionRepository, times(1)).findAll();

    }

    @Test
    void testFindById() {

        var promotionId = 1L;

        Promotion promotion = Datos.createPromotion().get();

        when(promotionRepository.findById(promotionId)).thenReturn(Optional.of(promotion));

        Promotion result = promotionServices.findById(promotionId);

        assertNotNull(result.getId());
        assertEquals(promotion, result);
        verify(promotionRepository, times(1)).findById(promotionId);

    }

    @Test
    void testSave() {
        var promotion = new Promotion(null,
                "Promotion3",
                LocalDate.parse("2024-03-06"), LocalDate.parse("2024-04-01"),
                90, new VideoGame());
        doAnswer(invocation -> {
            Promotion promotionTemporary = invocation.getArgument(0);
            promotionTemporary.setId(3L);
            return promotionTemporary;
        }).when(promotionRepository).save(promotion);

        Promotion result = promotionServices.save(promotion);

        verify(promotionRepository, times(1)).save(promotion);
        assertNotNull(result.getId());
        assertEquals(3L,result.getId());
    }

    @Test
    void deleteById() {
    }

    @Test
    void getPromotionSearchVideogameById() {
    }
}