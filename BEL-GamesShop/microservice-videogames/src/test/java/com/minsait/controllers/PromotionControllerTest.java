package com.minsait.controllers;

import com.minsait.services.IPromotionServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PromotionControllerTest {

    @Autowired
    IPromotionServices promotionServices;
    @Autowired
    private MockMvc mvc;

    @Test
    void findAllPromotion() {

    }

    @Test
    void findByIdPromotion() {
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