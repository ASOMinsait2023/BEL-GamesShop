package com.minsait.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "videogames")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VideoGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(name = "release_date")
    private String releaseDate;
    private BigDecimal price;

    @JsonIgnore
    @OneToMany(mappedBy = "videogame", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Promotion> promotions;

    public int calculatePromotionCount() {
        return this.promotions != null ? this.promotions.size() : 0;
    }
}
