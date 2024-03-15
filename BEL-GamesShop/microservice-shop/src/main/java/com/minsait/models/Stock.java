package com.minsait.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minsait.models.dto.VideoGameDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop")
    private Shop shop;
    private Long videogame;

    @Transient
    private VideoGameDTO videoGameDTO;
    private Integer stock;
}
