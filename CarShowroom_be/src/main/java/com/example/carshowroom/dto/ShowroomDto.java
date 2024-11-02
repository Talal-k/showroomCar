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
public class ShowroomDto implements Serializable {
    private Long id;
    private String name;
    private Long commercialRegistrationNumber;
    private String managerName;
    private Long contactNumber;
    private String address;
}