package com.minsait.microservicecategories.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_category")
    private String nameCategory;
    private String description;

    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "category_platform",
            joinColumns = @JoinColumn(name = "id_category", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_platform", referencedColumnName = "idPlatform"))
    private List<Platform> platforms;
}
