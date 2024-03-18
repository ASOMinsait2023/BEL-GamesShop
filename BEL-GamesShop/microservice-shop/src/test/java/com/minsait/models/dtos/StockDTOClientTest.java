package com.minsait.models.dtos;
import com.minsait.models.Shop;
import com.minsait.models.dto.StockDTOClient;
import com.minsait.models.dto.VideoGameDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockDTOClientTest {

    @Test
    void testStockDTOClientConstructor() {
        Long id = 1L;
        Shop shop = new Shop();
        VideoGameDTO videoGameDTO = new VideoGameDTO();
        Integer stock = 10;

        StockDTOClient stockDTOClient = new StockDTOClient(id, shop, videoGameDTO, stock);

        assertEquals(id, stockDTOClient.getId());
        assertEquals(shop, stockDTOClient.getShop());
        assertEquals(videoGameDTO, stockDTOClient.getVideoGameDTO());
        assertEquals(stock, stockDTOClient.getStock());
    }

    @Test
    void testStockDTOClientSettersAndGetters() {
        StockDTOClient stockDTOClient = new StockDTOClient();

        Long id = 1L;
        Shop shop = new Shop();
        VideoGameDTO videoGameDTO = new VideoGameDTO();
        Integer stock = 10;

        stockDTOClient.setId(id);
        stockDTOClient.setShop(shop);
        stockDTOClient.setVideoGameDTO(videoGameDTO);
        stockDTOClient.setStock(stock);

        assertEquals(id, stockDTOClient.getId());
        assertEquals(shop, stockDTOClient.getShop());
        assertEquals(videoGameDTO, stockDTOClient.getVideoGameDTO());
        assertEquals(stock, stockDTOClient.getStock());
    }
}
