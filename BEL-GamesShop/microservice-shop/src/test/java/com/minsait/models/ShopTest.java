package com.minsait.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import com.minsait.repositories.IStockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ShopTest {

    @InjectMocks
    private Shop shop;

    @Mock
    private IStockRepository stockRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGettersAndSetters() {
        // Set up
        Long id = 1L;
        String name = "Test Shop";
        String address = "123 Test St";
        String openingHour = "09:00";
        String closedHour = "18:00";
        String phoneNumber = "1234567890";
        List<Stock> stocks = Arrays.asList(new Stock(), new Stock());

        // Execute
        shop.setId(id);
        shop.setName(name);
        shop.setAddress(address);
        shop.setOpeningHour(openingHour);
        shop.setClosedHour(closedHour);
        shop.setPhoneNumber(phoneNumber);
        shop.setStocks(stocks);

        // Verify
        assertEquals(id, shop.getId());
        assertEquals(name, shop.getName());
        assertEquals(address, shop.getAddress());
        assertEquals(openingHour, shop.getOpeningHour());
        assertEquals(closedHour, shop.getClosedHour());
        assertEquals(phoneNumber, shop.getPhoneNumber());
        assertEquals(stocks, shop.getStocks());
    }
    @Test
    public void testNoArgsConstructor() {
        Shop shop = new Shop();
        assertNotNull(shop);
    }
}
