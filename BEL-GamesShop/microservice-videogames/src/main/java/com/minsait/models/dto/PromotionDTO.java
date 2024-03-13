package com.minsait.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@AllArgsConstructor
public class PromotionDTO {

    private Long id;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer percentage;
    private Long idVideoGame;
    private String name;
    private BigDecimal price;


}
