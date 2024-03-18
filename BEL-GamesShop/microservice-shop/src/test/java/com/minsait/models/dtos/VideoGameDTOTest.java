package com.minsait.models.dtos;
import com.minsait.models.Shop;
import com.minsait.models.Stock;
import com.minsait.models.dto.VideoGameDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class VideoGameDTOTest {
    @Test
    void testAllArgsConstructor() {
        Long id = 1L;
        Shop shop = new Shop();
        Long videoGameId = 10L;
        VideoGameDTO videoGameDTO = new VideoGameDTO();
        Integer stock = 20;

        Stock stockItem = new Stock(id, shop, videoGameId, videoGameDTO, stock);

        assertNotNull(stockItem);
        assertEquals(id, stockItem.getId());
        assertEquals(shop, stockItem.getShop());
        assertEquals(videoGameId, stockItem.getVideogame());
        assertEquals(videoGameDTO, stockItem.getVideoGameDTO());
        assertEquals(stock, stockItem.getStock());
    }

    @Test
    void testVideoGameDTO() {
        Long id = 1L;
        Shop shop = new Shop();
        Long videoGameId = 10L;
        VideoGameDTO videoGameDTO = new VideoGameDTO();
        Integer stock = 20;

        Stock stockItem = new Stock();
        stockItem.setId(id);
        stockItem.setShop(shop);
        stockItem.setVideogame(videoGameId);
        stockItem.setVideoGameDTO(videoGameDTO);
        stockItem.setStock(stock);

        assertEquals(videoGameDTO, stockItem.getVideoGameDTO());
    }
}
