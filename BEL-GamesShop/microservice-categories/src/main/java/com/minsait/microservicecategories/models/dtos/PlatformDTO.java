package com.minsait.microservicecategories.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlatformDTO {
    private Long id;
    private String name;
    private String publisher;
    private String generation;
    private Set<CategoryDTO> categories;
}
