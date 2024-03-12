package com.minsait.controllers;

import com.minsait.services.IVideoGameServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class VideoGameControllerTest {

    @Autowired
    IVideoGameServices videoGameServices;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAllVideoGame() {
        when(videoGameServices.findAll());
    }

    @Test
    void findById() {
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