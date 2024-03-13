package com.minsait.models.dto;

import lombok.Data;

@Data
public class StockDTO {
        private Long id;
        private Long videogame;
        private Integer stock;

        public StockDTO(Long id, Long videogame, Integer stock) {
            this.id = id;
            this.videogame = videogame;
            this.stock = stock;
        }
}
