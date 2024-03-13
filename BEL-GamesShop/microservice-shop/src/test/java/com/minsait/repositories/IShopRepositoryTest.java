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
        // Dado un valor de dirección conocido
        String address = "Av. Ejército Nacional Mexicano 843-B, Granada, Miguel Hidalgo, 11520 Ciudad de México, CDMX";

        // Cuando se realiza la consulta utilizando el método del repositorio
        List<Shop> shops = shopRepository.queryForAddress(address);

        // Entonces se espera que la lista de tiendas no esté vacía
        assertFalse(shops.isEmpty());

        // También puedes realizar otras aserciones según tu lógica de negocio
        // Por ejemplo, verificar que las tiendas obtenidas coincidan con la dirección esperada
        for (Shop shop : shops) {
            assertEquals(address, shop.getAddress());
        }
    }
}
