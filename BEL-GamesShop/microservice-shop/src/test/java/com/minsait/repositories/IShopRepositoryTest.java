package com.minsait.repositories;
import com.minsait.models.Shop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class IShopRepositoryTest {
    @Autowired
    IShopRepository shopRepository;
    @Test
    public void testQueryForAddress() {
        String address = "Av. Ejército Nacional Mexicano 843-B, Granada, Miguel Hidalgo, 11520 Ciudad de México, CDMX";

        List<Shop> shops = shopRepository.queryForAddress(address);

        assertTrue(shops.isEmpty());
        for (Shop shop : shops) {
            assertEquals(address, shop.getAddress());
        }
    }
}
