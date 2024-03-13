package com.minsait.microservicecategories.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "platforms")
@AllArgsConstructor
@NoArgsConstructor
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlatform;
    @Column(name = "name_platform")
    private String namePlatform;
    private String publisher;
    private String generation;
}
