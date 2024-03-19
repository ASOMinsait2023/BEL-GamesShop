package com.minsait.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryByVideoGameDTO {

    private Long id;
    private String name;
    private String description;
    private String releaseDate;
    private BigDecimal price;
    List<CategoryDTO> categoryDTOList;
}
