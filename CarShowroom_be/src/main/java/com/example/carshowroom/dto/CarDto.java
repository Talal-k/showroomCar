package com.example.carshowroom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CarDto implements Serializable {
    private Long id;
    private String vin;
    private String maker;
    private String model;
    private Long modelYear;
    private Double price;
    private ShowroomCarDto showroom;
}