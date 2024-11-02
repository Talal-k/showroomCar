package com.example.carshowroom.model;


import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "vin", nullable = false, length = 25)
    private String vin;
    @Column(name = "maker", nullable = false, length = 25)
    private String maker;
    @Column(name = "model", length = 25,nullable = false)
    private String model;
    @Column(name = "model_year", length = 4,nullable = false)
    private Long modelYear;
    @Column(name = "price", length = 255,nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showroom_id", nullable = false)
    private Showroom showroom;

}
