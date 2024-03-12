    package com.minsait.models;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.math.BigDecimal;
    import java.time.LocalDate;

    @Data
    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    @Table(name = "promotion")
    public class Promotion {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String description;
        @Column(name = "start_date")
        private LocalDate startDate;
        @Column(name = "end_date")
        private LocalDate endDate;
        private Integer percentage;

        @ManyToOne
        @JoinColumn(name = "videogame")
        private VideoGame videogame;


        public BigDecimal calculateDiscountedPrice(BigDecimal originalPrice) {
            LocalDate today = LocalDate.now();

            if (startDate != null && endDate != null && !today.isBefore(startDate) && !today.isAfter(endDate)) {
                if (percentage != null && percentage >= 0 && percentage <= 100) {
                    BigDecimal discountMultiplier = BigDecimal.valueOf(100 - percentage).divide(BigDecimal.valueOf(100));
                    return originalPrice.multiply(discountMultiplier);
                }
            }

            return originalPrice;
        }
    }
