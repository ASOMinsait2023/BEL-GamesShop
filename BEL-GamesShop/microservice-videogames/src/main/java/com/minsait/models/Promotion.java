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



    }
