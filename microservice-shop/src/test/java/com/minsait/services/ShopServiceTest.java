package com.minsait.services;
import com.minsait.models.Shop;
import com.minsait.repositories.IShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ShopServiceTest {
    @Mock
    private IShopRepository shopRepository;

    @InjectMocks
    private ShopServiceImpl shopService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {
        List<Shop> shops = new ArrayList<>();
        shops.add(new Shop());
        when(shopRepository.findAll()).thenReturn(shops);
        List<Shop> result = shopService.findAll();
        assertNotNull(result);
        assertEquals(shops, result);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Shop shop = new Shop();
        when(shopRepository.findById(id)).thenReturn(Optional.of(shop));
        Shop result = shopService.findById(id);
        assertNotNull(result);
        assertEquals(shop, result);
    }

    @Test
    void testSave() {
        Shop shop = new Shop();
        when(shopRepository.save(shop)).thenReturn(shop);
        Shop result = shopService.save(shop);
        assertNotNull(result);
        assertEquals(shop, result);
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        Shop shop = new Shop();
        when(shopRepository.findById(id)).thenReturn(Optional.of(shop));
        doNothing().when(shopRepository).deleteById(id);
        assertTrue(shopService.delleteById(id));
        verify(shopRepository, times(1)).deleteById(id);
    }
    @Test
    void testDelleteByIdShopNotFound() {
        Long idShop = 1L;
        Optional<Shop> optionalShop = Optional.empty();
        when(shopRepository.findById(idShop)).thenReturn(optionalShop);
        assertFalse(shopService.delleteById(idShop));
        verify(shopRepository, never()).deleteById(idShop);
    }

    @Test
    void testQueryForAddress() {
        String address = "Av. Principal 123";

        List<Shop> shops = List.of(new Shop(), new Shop());
        when(shopRepository.queryForAddress(address)).thenReturn(shops);

        List<Shop> result = shopService.queryForAddress(address);
        assertEquals(shops, result);
    }

}
