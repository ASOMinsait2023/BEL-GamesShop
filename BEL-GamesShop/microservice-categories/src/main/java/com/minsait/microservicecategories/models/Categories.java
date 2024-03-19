package com.minsait.microservicecategories.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
public class Categories implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_category", unique = true)
    private String nameCategory;
    private String description;
    @Column(name = "video_game_id")
    private Long videoGameId;

    //@ManyToMany(mappedBy = "categories")
    @JsonIgnoreProperties(value = {"categories", "hibernateLazyInitializer"}, allowSetters = true)
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "platform_id")
    private Platform platform;
    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform")
    private Platform platform;*/

}
