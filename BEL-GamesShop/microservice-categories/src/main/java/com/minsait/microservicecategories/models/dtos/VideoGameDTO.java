package com.minsait.microservicecategories.models.dtos;

import lombok.Data;
import java.math.BigDecimal;
@Data
public class VideoGameDTO {

    private Long id;
    private String name;
    private String description;
    private String releaseDate;
    private BigDecimal price;
    private Long categoryId;
}
