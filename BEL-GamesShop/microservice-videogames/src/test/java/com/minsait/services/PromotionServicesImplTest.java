package com.minsait.services;

import com.minsait.repositories.IPromotionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PromotionServicesImplTest {

    @Mock
    IPromotionRepository promotionRepository;

    @InjectMocks
    PromotionServicesImpl promotionServices;

    @Test
    void testFindAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void getPromotionSearchVideogameById() {
    }
}