package com.minsait.services;

import com.minsait.controllers.Datos;
import com.minsait.models.Promotion;
import com.minsait.models.VideoGame;
import com.minsait.repositories.IVideoGameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VideoGameServiceImplTest {

    @Mock
    private IVideoGameRepository videoGameRepository;

    @InjectMocks
    private VideoGameServiceImpl videoGameServices;


    @Test
    void testFindAll() throws Exception{

        List<VideoGame> videoGamesList = List.of(
                Datos.createVideogame1().get(),
                Datos.createVideogame2().get(),
                Datos.createVideogame3().get()
        );

        when(videoGameRepository.findAll()).thenReturn(videoGamesList);

        List<VideoGame> result = videoGameServices.findAll();

        assertEquals(3, result.size());
        assertEquals("Left 4 Dead 2", result.get(0).getName());
        assertEquals("Red Dead Redemption 2", result.get(1).getName());
        assertEquals("Dead by Daylight", result.get(2).getName());

        verify(videoGameRepository, times(1)).findAll();
    }

    @Test
    void testFindById() throws Exception {

        var videoGameId = 1L;

        VideoGame videoGame = Datos.createVideogame1().get();

        when(videoGameRepository.findById(videoGameId)).thenReturn(Optional.of(videoGame));

        VideoGame result = videoGameServices.findById(videoGameId);
        verify(videoGameRepository, times(1)).findById(videoGameId);

        assertEquals(videoGame, result);

    }

    @Test
    void testSave() throws Exception {
        var videogame = new VideoGame(null, "PAYDAY 2 ", " is an action-packed, four-player co-op " +
                "shooter that once again lets gamers don the masks of the original PAYDAY crew - Dallas, Hoxton, " +
                "Wolf and Chains - as they descend on Washington DC for an epic crime spree",
                "2013-08-13", new BigDecimal("280.50"),List.of( new Promotion()));
        doAnswer(invocation -> {
            VideoGame videoGameTemporary = invocation.getArgument(0);
            videoGameTemporary.setId(4L);
            return videoGameTemporary;
        }).when(videoGameRepository).save(videogame);

        VideoGame result = videoGameServices.save(videogame);
        verify(videoGameRepository, times(1)).save(videogame);
        assertNotNull(result.getId());
        assertEquals(4L, result.getId());


    }

    @Test
    void testDeleteById_ExistingVideoGame() {

        Long videoGameId = 1L;
        VideoGame videoGame = Datos.createVideogame3().get();
        when(videoGameRepository.findById(videoGameId)).thenReturn(Optional.of(videoGame));

        boolean result = videoGameServices.deleteById(videoGameId);

        assertTrue(result);
        verify(videoGameRepository, times(1)).deleteById(videoGameId);
    }

    @Test
    void testDeleteById_NonExistingVideoGame() throws Exception {

        Long videoGameId = 1L;
        when(videoGameRepository.findById(videoGameId)).thenReturn(Optional.empty());

        boolean result = videoGameServices.deleteById(videoGameId);

        assertFalse(result);
        verify(videoGameRepository, never()).deleteById(videoGameId);
    }
    @Test
    void testGetVideoGameWithDiscount() throws Exception{
        Long videoGameId = 1L;
        VideoGame videoGame1 = Datos.createVideogame1().get();

        when(videoGameRepository.findVideoGameWithDiscount(videoGameId)).thenReturn(Collections.singletonList(videoGame1));

        List<VideoGame> result = videoGameServices.getVideoGameWithDiscount(videoGameId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(videoGame1, result.get(0));
        verify(videoGameRepository, times(1)).findVideoGameWithDiscount(videoGameId);
    }
}