package com.minsait.models.dto;

import com.minsait.models.Shop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockDTOClient {
    private Long id;
    private Shop shop;
    private VideoGameDTO videoGameDTO;
    private Integer stock;

}
