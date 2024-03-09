package com.minsait.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Stock")
public class Stock {
    private Long id;
    private Long shop;
    private Long videogame;
    private Integer stock;
}
