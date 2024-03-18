package com.minsait.models;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shops")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    @Column(name = "opening_hour")
    private String openingHour;
    @Column(name = "closed_hour")
    private String closedHour;
    @Column(name = "phone_number")
    private String phoneNumber;
    @JsonIgnore
    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Stock> stocks;
    public Shop(Long id, String name, String address, String openingHour, String closedHour, String phoneNumber){
        this.id = id;
        this.name = name;
        this.address = address;
        this.openingHour = openingHour;
        this.closedHour = closedHour;
        this.phoneNumber = phoneNumber;
    }
}
